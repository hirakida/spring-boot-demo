package com.example

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class UserService(private val jdbcTemplate: JdbcTemplate) {

    fun findById(id: Int): User =
        jdbcTemplate.queryForObject("SELECT id, name FROM user WHERE id=$id")
        { rs, _ -> toUser(rs) } ?: throw NoSuchElementException("id: $id")

    fun findAll(): List<User> =
        jdbcTemplate.query("SELECT * FROM user")
        { rs, _ -> toUser(rs) }

    private fun toUser(rs: ResultSet): User =
        User(rs.getInt("id"), rs.getString("name"))
}
