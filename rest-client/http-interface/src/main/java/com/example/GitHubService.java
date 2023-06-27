package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.example.model.Key;
import com.example.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GitHubService {
    @GetExchange("/users/{username}")
    Mono<User> getUser(@PathVariable String username);

    @GetExchange("/users/{username}/keys")
    Flux<Key> getKeys(@PathVariable String username);
}
