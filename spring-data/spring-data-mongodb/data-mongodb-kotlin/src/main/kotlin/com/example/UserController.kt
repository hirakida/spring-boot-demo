package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserController(private val repository: UserRepository) {
    @GetMapping
    fun findAll(): Flow<User> = repository.findAll()

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: String): User =
        repository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
}
