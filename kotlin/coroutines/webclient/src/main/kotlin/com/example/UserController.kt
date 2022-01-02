package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToFlow

@RestController
class UserController(private val webClient: WebClient) {

    @GetMapping("/users/{username}/following")
    fun getFollowing(@PathVariable username: String): Flow<Follow> =
        webClient.get()
            .uri("/users/{username}/following", username)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlow()

    @GetMapping("/users/{username}")
    suspend fun getUser(@PathVariable username: String): User =
        webClient.get()
            .uri("/users/{username}", username)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .awaitBody()
}
