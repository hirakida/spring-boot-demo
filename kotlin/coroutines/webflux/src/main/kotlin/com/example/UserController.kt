package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/users")
    fun findAll(): Flow<User> {
        return (1..5).map { User(it, "name$it") }.asFlow()
    }

    @GetMapping("/users/{id}")
    suspend fun findOne(@PathVariable id: Int): User {
        delay(1000)
        return User(id, "name$id")
    }
}
