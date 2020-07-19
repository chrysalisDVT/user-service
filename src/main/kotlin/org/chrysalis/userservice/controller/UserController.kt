package org.chrysalis.userservice.controller

import org.chrysalis.userservice.model.User
import org.chrysalis.userservice.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
class UserController(@Autowired val userService:UserService){
    @GetMapping("/users")
    fun getUsersDetails(): Flux<User>? {
        return userService.getUserDetails()?.stream()?.let { Flux.fromStream(it) }
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id:String){

    }

    @GetMapping("/user")
    fun paginatedUserDetails(pageInfo:Pageable){

    }

    @PostMapping("/register")
    fun registerUser(@RequestBody user:User)  = userService.saveUser(user)

    @PutMapping("/user")
    fun updateUser(@RequestBody user:User){

    }

    @PatchMapping("/user")
    fun mergeUser(@RequestBody user:User){

    }

}

@RestControllerAdvice
class UserControllerAdvice{

}