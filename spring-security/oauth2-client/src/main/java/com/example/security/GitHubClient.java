package com.example.security;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.security.model.Repo;
import com.example.security.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GitHubClient {
    private final WebClient webClient;

    public Mono<User> getUser() {
        return webClient.get()
                        .uri("/user")
                        .retrieve()
                        .bodyToMono(User.class);
    }

    public Flux<Repo> getUserRepos() {
        return webClient.get()
                        .uri("/user/repos")
                        .retrieve()
                        .bodyToFlux(Repo.class);
    }
}
