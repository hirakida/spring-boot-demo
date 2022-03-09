package com.example

import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {

    fun selectAll(): List<User> =
        Users.selectAll().map { User(it[Users.id], it[Users.name]) }

    fun select(id: Int): User =
        Users.select { Users.id eq id }.map { User(it[Users.id], it[Users.name]) }.single()

    fun insert(user: User) =
        Users.insert {
            it[name] = user.name
        }

    fun insert(users: List<User>) =
        Users.batchInsert(users) {
            this[Users.name] = it.name
        }

    fun update(user: User) =
        Users.update({ Users.id eq user.id }) {
            it[name] = user.name
        }

    fun deleteAll() = Users.deleteAll()

    fun delete(id: Int) = Users.deleteWhere { Users.id eq id }
}
