package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Component
public class RandomApiClient {
    private final WebClient webClient;

    public RandomApiClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<Random> getRandom() {
        return webClient.get()
                        .uri("/random")
                        .retrieve()
                        .bodyToMono(Random.class);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Random {
        private long random;
    }
}
