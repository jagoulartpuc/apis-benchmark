package assetbrains.coreapi.controller

import assetbrains.coreapi.domain.User
import assetbrains.coreapi.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): Mono<User> {
        return userService.createUser(user)
    }

    @GetMapping("/{username}")
    fun getUserByUsername(@PathVariable username: String?): Mono<User?>? {
        return userService.findByUsername(username)
    }

    @GetMapping
    fun getAllUsers(): Flux<User?>? {
        return userService.findAll()
    }
}

