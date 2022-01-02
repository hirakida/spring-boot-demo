package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val repository: UserRepository) {

    @GetMapping("/users")
    fun findAll(): Flow<User> = repository.findAll()

    @GetMapping("/users/{id}")
    suspend fun findOne(@PathVariable id: String): User = repository.findOne(id)

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody user: User): User = repository.insert(user)

    @PutMapping("/users/{id}")
    suspend fun update(@PathVariable id: String, @RequestBody user: User) {
        repository.update(user)
    }
}
