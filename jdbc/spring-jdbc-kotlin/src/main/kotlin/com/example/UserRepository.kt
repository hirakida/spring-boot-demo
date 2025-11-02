package com.example

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class UserRepository(private val jdbcTemplate: JdbcTemplate) {

    fun getReferenceById(id: Int): User =
        jdbcTemplate.queryForObject("SELECT id, name FROM `user` WHERE id=$id")
        { rs, _ -> toUser(rs) } ?: throw NoSuchElementException("id: $id")

    fun findAll(): List<User> =
        jdbcTemplate.query("SELECT id, name FROM `user`")
        { rs, _ -> toUser(rs) }

    private fun toUser(rs: ResultSet): User =
        User(rs.getInt("id"), rs.getString("name"))
}
