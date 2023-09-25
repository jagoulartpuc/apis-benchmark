package main

import (
	"log"
	"net/http"

	"github.com/gin-gonic/gin"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

// User represents the user model
type User struct {
	Username string `json:"username"`
	Email    string `json:"email"`
	Password string `json:"password"`
}

var client *mongo.Client


func init() {
	// Replace with your MongoDB URI
	mongoURI := "mongodb://localhost:27017"
	clientOptions := options.Client().ApplyURI(mongoURI)
	client, _ = mongo.Connect(nil, clientOptions)
}

func main() {
	r := gin.Default()

	r.POST("/user", createUser)
	r.GET("/user/:username", getUserByUsername)
	r.GET("/user", getUsers)

	port := "5000" // Replace with your desired port
	err := r.Run(":" + port)
	if err != nil {
		log.Fatal(err)
	}
}

func createUser(c *gin.Context) {
	var user User
	if err := c.ShouldBindJSON(&user); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	_, err := client.Database("coredb").Collection("user").InsertOne(nil, user)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusCreated, user)
}

func getUserByUsername(c *gin.Context) {
	username := c.Param("username")

	// Fetch the user from MongoDB based on the username
	var user User
	err := client.Database("coredb").Collection("user").FindOne(nil, bson.M{"username": username}).Decode(&user)
	if err != nil {
		if err == mongo.ErrNoDocuments {
			c.JSON(http.StatusNotFound, gin.H{"error": "User not found"})
		} else {
			c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		}
		return
	}

	c.JSON(http.StatusOK, user)
}

func getUsers(c *gin.Context) {
	// Fetch all users from MongoDB
	cursor, err := client.Database("coredb").Collection("user").Find(nil, bson.M{})
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}
	defer cursor.Close(nil)

	var users []User
	err = cursor.All(nil, &users)
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, users)
}
