package com.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.jdbc.core.JdbcTemplate

@JdbcTest
class UserServiceTest {
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate
    lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService = UserService(jdbcTemplate)
    }

    @Test
    fun findAll() {
        val users = userService.findAll()
        assertEquals(users.size, 5)
    }
}
