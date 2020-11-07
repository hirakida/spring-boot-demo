package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.model.Follow;
import com.example.model.Key;
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubApiClient client;

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return client.getUser(username);
    }

    @GetMapping("/users/{username}/keys")
    public Key[] getKeys(@PathVariable String username) {
        return client.getKeys(username);
    }

    @GetMapping("/users/{username}/followers")
    public Follow[] getFollowers(@PathVariable String username) {
        return client.getFollowers(username);
    }

    @GetMapping("/users/{username}/following")
    public Follow[] getFollowing(@PathVariable String username) {
        return client.getFollowing(username);
    }
}
