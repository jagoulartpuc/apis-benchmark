package com.example.plugins

import com.example.User
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

fun Application.configureRouting() {
    val client = KMongo.createClient().coroutine
    val database = client.getDatabase("coredb")
    val collection = database.getCollection<User>()

    routing {
        route("/user") {
            post {
                try {
                    val user = call.receive<User>()
                    collection.insertOne(user)
                    call.respond(HttpStatusCode.Created, "User saved!")
                } catch (e: Exception) {
                    println("Error while inserting user: ${e.message}")
                    call.respond(HttpStatusCode.BadRequest, "Error while processing the request")
                }
            }

            get("/{username}") {
                val username = call.parameters["username"] ?: ""
                val user = collection.findOne("{ 'username': '$username' }")
                if (user != null) {
                    call.respond(user)
                } else {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                }
            }

            get {
                val users = collection.find().toList().toMutableList()
                call.respond(users)
            }
        }
    }
}
