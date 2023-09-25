package assetbrains.coreapi.repository

import assetbrains.coreapi.domain.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User?, ObjectId?> {
    fun findByUsername(username: String?): User?
}

