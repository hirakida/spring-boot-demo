package com.example

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import scala.jdk.CollectionConverters.*
import scala.jdk.javaapi.OptionConverters.toScala

@Service
@Transactional
class UserService(val userRepository: UserRepository) {

  def findAll(): Iterable[User] = userRepository.findAll().asScala

  def findById(id: Int): Option[User] = toScala(userRepository.findById(id))

  def create(user: User): User = userRepository.save(user)

  def update(user: User): User = {
    val current = findById(user.id).getOrElse(throw new EntityNotFoundException())
    val updated = current.copy(name = user.name, age = user.age)
    userRepository.save(updated)
  }

  def delete(id: Int): Unit = userRepository.deleteById(id)
}
