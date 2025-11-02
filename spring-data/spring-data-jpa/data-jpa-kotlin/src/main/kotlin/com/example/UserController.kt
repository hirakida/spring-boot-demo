package com.example

import jakarta.persistence.EntityNotFoundException
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Range
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class UserController(private val userRepository: UserRepository) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping("/users")
    fun findAll(): List<User> = userRepository.findAll()

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id: Int): User = userRepository.getReferenceById(id)

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Validated request: Request): ResponseEntity<User> {
        val user = User(name = request.name, age = request.age)
        val created = userRepository.save(user)
        return ResponseEntity.created(URI.create("/api/users/" + created.id)).body(created)
    }

    @PutMapping("/users/{id}")
    fun update(@PathVariable id: Int, @RequestBody @Validated request: Request): User {
        val user = userRepository.getReferenceById(id)
        user.name = request.name
        user.age = request.age
        return userRepository.save(user)
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        val user = userRepository.getReferenceById(id)
        userRepository.deleteById(user.id)
    }

    @ExceptionHandler(EntityNotFoundException::class, NoSuchElementException::class)
    fun handleNotFoundException(e: RuntimeException): ResponseEntity<Any> {
        log.warn(e.message, e)
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    data class Request(
        @field:NotEmpty val name: String,
        @field:Range(min = 20, max = 60) val age: Byte
    )
}
