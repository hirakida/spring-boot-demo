package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserController(private val repository: UserRepository) {

    @GetMapping
    fun findAll(): Flow<User> = repository.findAll()

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Int): User =
        repository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody user: User): User = repository.save(user)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun update(@PathVariable id: Int, @RequestBody user: User) {
        if (id != user.id) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        repository.save(user)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable id: Int) {
        repository.deleteById(id)
    }
}
