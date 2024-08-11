package com.example

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

import java.net.URI
import scala.jdk.CollectionConverters.*

@RestController
class UserController(val userService: UserService) {
  @GetMapping(path = Array("/users"))
  def findAll(): java.lang.Iterable[User] = userService.findAll().asJava

  @GetMapping(path = Array("/users/{id}"))
  def findById(@PathVariable id: Int): User = userService.findById(id)
    .getOrElse(throw new ResponseStatusException(HttpStatus.NOT_FOUND))

  @PostMapping(path = Array("/users"))
  def create(@RequestBody @Validated request: UserRequest): ResponseEntity[User] = {
    val user = new User(name = request.name, age = request.age)
    val created = userService.create(user)
    ResponseEntity.created(URI.create("/users/" + created.id)).body(created)
  }

  @PutMapping(path = Array("/users/{id}"))
  def update(@PathVariable id: Int, @RequestBody @Validated request: UserRequest): User = {
    val user = new User(id = id, name = request.name, age = request.age)
    userService.update(user)
  }

  @DeleteMapping(path = Array("/users/{id}"))
  @ResponseStatus(HttpStatus.NO_CONTENT)
  def delete(@PathVariable id: Int): Unit = userService.delete(id)

  @ExceptionHandler(Array(classOf[EntityNotFoundException]))
  def handleResponseStatusException(): ResponseEntity[AnyRef] = {
    new ResponseEntity(HttpStatus.NOT_FOUND)
  }
}
