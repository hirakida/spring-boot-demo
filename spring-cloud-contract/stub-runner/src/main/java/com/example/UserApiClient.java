package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class UserApiClient {
    private final WebClient webClient;

    public UserApiClient(WebClient.Builder builder, UserApiProperties properties) {
        final String uri = UriComponentsBuilder.newInstance()
                .scheme(properties.scheme())
                .host(properties.host())
                .port(properties.port())
                .build()
                .toUriString();
        webClient = builder.baseUrl(uri).build();
    }

    public Mono<User> getUser(long id) {
        return webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }
}
