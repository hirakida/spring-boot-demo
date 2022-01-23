package com.example

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Int): User = userRepository.getById(id)

    fun create(user: User): User = userRepository.save(user)

    fun update(user: User): User {
        val current = userRepository.getById(user.id)
        current.name = user.name
        current.age = user.age
        return userRepository.save(current)
    }

    fun delete(id: Int) {
        val user = userRepository.getById(id)
        userRepository.deleteById(user.id)
    }
}
