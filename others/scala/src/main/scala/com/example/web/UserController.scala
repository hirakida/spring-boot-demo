package com.example.web

import java.net.URI

import com.example.entity.User
import com.example.service.UserService
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation._

import scala.jdk.CollectionConverters._

@RestController
@RequestMapping(Array("/users"))
class UserController(val userService: UserService) {

  @GetMapping
  def findAll(): java.lang.Iterable[User] = userService.findAll().asJava

  @GetMapping(params = Array("name"))
  def findByName(@RequestParam name: String): java.lang.Iterable[User] = userService.findByName(name).asJava

  @GetMapping(Array("/{id}"))
  def getOne(@PathVariable id: Int): User = userService.getOne(id)

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  def create(@RequestBody @Validated request: UserRequest): ResponseEntity[User] = {
    val user = new User(name = request.getName, age = request.getAge)
    val created = userService.create(user)
    ResponseEntity.created(URI.create("/api/users/" + created.getId)).body(created)
  }

  @PutMapping(Array("/{id}"))
  def update(@PathVariable id: Int, @RequestBody @Validated request: UserRequest): User = {
    val user = new User(id = id, name = request.getName, age = request.getAge)
    userService.update(user)
  }

  @DeleteMapping(Array("/{id}"))
  @ResponseStatus(HttpStatus.NO_CONTENT)
  def delete(@PathVariable id: Int): Unit = userService.delete(id)
}
