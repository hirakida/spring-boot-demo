package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class UserApiClient {
    private final WebClient webClient;

    public UserApiClient(WebClient.Builder builder, UserApiProperties properties) {
        webClient = builder.baseUrl(properties.getBaseUrl()).build();
    }

    public Mono<User> getUser(long id) {
        return webClient.get()
                        .uri("/users/{id}", id)
                        .retrieve()
                        .bodyToMono(User.class);
    }
}
