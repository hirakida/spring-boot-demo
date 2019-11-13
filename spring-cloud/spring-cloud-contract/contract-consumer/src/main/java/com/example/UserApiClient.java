package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Component
public class UserApiClient {
    private final WebClient webClient;

    public UserApiClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<User> getUser(long id) {
        return webClient.get()
                        .uri("/users/{id}", id)
                        .retrieve()
                        .bodyToMono(User.class);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private long id;
        private String name;
    }
}
