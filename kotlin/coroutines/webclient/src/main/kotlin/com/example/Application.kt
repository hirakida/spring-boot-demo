package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class Application {
    @Bean
    fun webClient(builder: WebClient.Builder) = builder.baseUrl("https://api.github.com").build()
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
