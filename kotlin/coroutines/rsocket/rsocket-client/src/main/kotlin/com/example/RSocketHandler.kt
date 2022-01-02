package com.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.context.annotation.Bean
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.retrieveFlow
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.sse

data class GreetingRequest(val name: String)
data class GreetingResponse(val message: String)

@Component
class RSocketHandler(builder: RSocketRequester.Builder) {
    private val requester: RSocketRequester

    init {
        requester = builder.tcp("localhost", 7000)
    }

    @Bean
    fun route() =
        coRouter {
            GET("/greetings/{name}", ::greeting)
        }

    suspend fun greeting(serverRequest: ServerRequest): ServerResponse {
        val request = GreetingRequest(serverRequest.pathVariable("name"))
        val greetings: Flow<String> = requester.route("greetings")
            .data(request)
            .retrieveFlow<GreetingResponse>()
            .map { response -> response.message }
        return ok().sse().bodyAndAwait(greetings)
    }
}
