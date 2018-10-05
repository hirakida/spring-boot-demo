package com.example

import org.springframework.boot.kofu.application
import org.springframework.boot.kofu.web.jackson
import org.springframework.boot.kofu.web.server
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import java.time.LocalDateTime

val app = application {
    server {
        port = if (profiles.contains("test")) 8181 else 8080
        codecs {
            string()
            jackson()
        }
        import(::routes)
    }
}

fun routes() = router {
    GET("/") {
        ServerResponse
                .ok()
                .syncBody("Hello!")
    }
    GET("/api") {
        ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(Response(message = "Hello!", datetime = LocalDateTime.now())))
    }
}

data class Response(
        val message: String,
        val datetime: LocalDateTime
)

fun main(): Unit = app.run()
