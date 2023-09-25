package assetbrains.coreapi.controller

import assetbrains.coreapi.domain.User
import assetbrains.coreapi.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): User {
        return userService.createUser(user)
    }

    @GetMapping("/{username}")
    fun getUserByUsername(@PathVariable username: String?): User? {
        return userService.findByUsername(username)
    }

    @GetMapping
    fun getAllUsers(): List<User?>? {
        return userService.findAll()
    }
}

