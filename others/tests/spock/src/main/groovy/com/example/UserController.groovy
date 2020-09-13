package com.example

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
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
import org.springframework.web.context.request.WebRequest

import javax.validation.constraints.NotNull

@RestController
class UserController {
    static final Logger LOGGER = LoggerFactory.getLogger(this)
    final UserService userService

    UserController(UserService userService) {
        this.userService = userService
    }

    @GetMapping("/users")
    def findAll() {
        userService.findAll()
    }

    @GetMapping("/users/{id}")
    def findById(@PathVariable Integer id) {
        userService.findById(id)
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    def create(@RequestBody @Validated UserRequest request) {
        userService.create(request.name)
    }

    @PutMapping("/users/{id}")
    def update(@PathVariable Integer id, @RequestBody @Validated UserRequest request) {
        userService.update(id, request.name)
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        userService.delete(id)
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
