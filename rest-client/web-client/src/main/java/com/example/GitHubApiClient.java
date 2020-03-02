package com.example;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.Key;
import com.example.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {
    private final WebClient webClient;

    public Mono<User> getUser(String username) {
        return webClient.get()
                        .uri("/users/{username}", username)
                        .retrieve()
                        .bodyToMono(User.class)
                        .retryBackoff(3, Duration.ofSeconds(1));
    }

    public Flux<Key> getKeys(String username) {
        return webClient.get()
                        .uri("/users/{username}/keys", username)
                        .retrieve()
                        .bodyToFlux(Key.class);
    }
}
