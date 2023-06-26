package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Key;
import com.example.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class GitHubController {
    private final GitHubService service;

    public GitHubController(GitHubService gitHubService) {
        service = gitHubService;
    }

    @GetMapping("/users/{username}")
    public Mono<User> getUser(@PathVariable String username) {
        return service.getUser(username);
    }

    @GetMapping("/users/{username}/keys")
    public Flux<Key> getKeys(@PathVariable String username) {
        return service.getKeys(username);
    }
}
