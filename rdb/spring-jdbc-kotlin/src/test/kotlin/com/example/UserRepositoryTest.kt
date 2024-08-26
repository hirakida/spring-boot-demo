package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.Import

@JdbcTest
@Import(UserRepository::class)
class UserRepositoryTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun findAll() {
        val users = userRepository.findAll()
        assertEquals(users.size, 5)
    }
}
