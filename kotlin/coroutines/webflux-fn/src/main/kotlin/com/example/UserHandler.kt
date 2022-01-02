package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Component
class UserHandler {
    @Bean
    fun routes() = coRouter {
        "/users".nest {
            GET("", ::findAll)
            GET("/{id}", ::findById)
        }
    }

    @Suppress("UNUSED_PARAMETER")
    suspend fun findAll(request: ServerRequest): ServerResponse {
        val users: Flow<User> = (1..5).map { User(it, "name$it") }.asFlow()
        return ok().bodyAndAwait(users)
    }

    suspend fun findById(request: ServerRequest): ServerResponse {
        delay(1000)
        val id = request.pathVariable("id").toInt()
        return ok().bodyValueAndAwait(User(id, "name$id"))
    }
}
