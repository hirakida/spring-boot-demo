package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.model.Key;
import com.example.model.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubApiClient client;

    @GetMapping("/users/{username}")
    public Mono<User> getUser(@PathVariable String username) {
        return client.getUser(username);
    }

    @GetMapping("/users/{username}/keys")
    public Flux<Key> getKeys(@PathVariable String username) {
        return client.getKeys(username);
    }
}
