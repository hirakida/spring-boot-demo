package com.example

import jakarta.validation.constraints.NotNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest

@RestController
class UserController {
    static final Logger LOGGER = LoggerFactory.getLogger(this)
    final UserRepository userRepository

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @GetMapping("/users")
    def findAll() {
        userRepository.findAll()
    }

    @GetMapping("/users/{id}")
    def findById(@PathVariable("id") Integer id) {
        userRepository.findById(id).orElseThrow()
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    def create(@RequestBody @Validated UserRequest request) {
        final User user = new User(name: request.name)
        userRepository.save(user)
    }

    @PutMapping("/users/{id}")
    def update(@PathVariable("id") Integer id,
               @RequestBody @Validated UserRequest request) {
        final User user = findById(id)
        user.setName(request.name)
        userRepository.save(user)
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") Integer id) {
        userRepository.deleteById(id)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    def handleNoSuchElementException(NoSuchElementException e, WebRequest request) {
        LOGGER.error("request: {}", request, e)
    }

    static class UserRequest {
        @NotNull
        String name
    }
}
