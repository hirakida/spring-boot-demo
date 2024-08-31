package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class UserHandler {
    @Bean
    fun routes() = coRouter {
        "/users".nest {
            GET("", ::findAll)
            GET("/{id}", ::findById)
        }
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        val users: Flow<User> = (1..5).map { User(it, "name$it") }.asFlow()
        return ok().bodyAndAwait(users)
    }

    suspend fun findById(request: ServerRequest): ServerResponse {
        delay(1000)
        val id = request.pathVariable("id").toInt()
        return ok().bodyValueAndAwait(User(id, "name$id"))
    }

    data class User(val id: Int, val name: String)
}
