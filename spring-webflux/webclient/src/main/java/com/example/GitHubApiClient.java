package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.Key;
import com.example.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GitHubApiClient {
    private final WebClient webClient;

    public GitHubApiClient(WebClient.Builder builder,
                           @Value("${github.baseUrl:https://api.github.com}") String baseUrl) {
        webClient = builder.baseUrl(baseUrl).build();
    }

    public Mono<User> getUser(String username) {
        return webClient.get()
                        .uri("/users/{username}", username)
                        .retrieve()
                        .bodyToMono(User.class);
    }

    public Flux<Key> getKeys(String username) {
        return webClient.get()
                        .uri("/users/{username}/keys", username)
                        .retrieve()
                        .bodyToFlux(Key.class);
    }
}
