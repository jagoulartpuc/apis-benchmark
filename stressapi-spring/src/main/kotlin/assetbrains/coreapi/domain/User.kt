package assetbrains.coreapi.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "user")
data class User(@Id val id: ObjectId?, var username: String, var email: String, var password: String)
