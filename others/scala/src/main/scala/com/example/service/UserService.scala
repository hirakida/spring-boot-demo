package com.example.service

import com.example.entity.User
import com.example.repository.UserRepository
import javax.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import scala.compat.java8.OptionConverters._
import scala.jdk.CollectionConverters._

@Service
@Transactional
class UserService(val userRepository: UserRepository) {

  def findAll(): Iterable[User] = userRepository.findAll().asScala

  def findByName(name: String): Iterable[User] = userRepository.findByName(name).asScala

  def findById(id: Int): Option[User] = toScala(userRepository.findById(id))

  def getOne(id: Int): User = findById(id).getOrElse(throw new EntityNotFoundException())

  def create(user: User): User = userRepository.save(user)

  def update(user: User): User = {
    val current = getOne(user.getId)
    current.setName(user.getName)
    current.setAge(user.getAge)
    userRepository.save(current)
  }

  def delete(id: Int): Unit = {
    val user = getOne(id)
    userRepository.deleteById(user.getId)
  }
}
