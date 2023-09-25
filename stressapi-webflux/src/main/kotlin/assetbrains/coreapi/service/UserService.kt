package assetbrains.coreapi.service

import assetbrains.coreapi.domain.User
import assetbrains.coreapi.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(user: User): Mono<User> {
        return userRepository.save<User>(user)
    }

    fun findByUsername(username: String?): Mono<User?>? {
        return userRepository.findByUsername(username)
    }

    fun findAll(): Flux<User?>? {
        return userRepository.findAll();
    }
}

