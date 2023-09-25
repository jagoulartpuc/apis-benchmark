package assetbrains.coreapi.service

import assetbrains.coreapi.domain.User
import assetbrains.coreapi.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun findByUsername(username: String?): User? {
        return userRepository.findByUsername(username)
    }

    fun findAll(): List<User?>? {
        return userRepository.findAll();
    }
}

