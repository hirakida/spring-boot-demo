package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient1;
import com.example.client.GitHubApiClient2;
import com.example.model.Follow;
import com.example.model.Key;
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubApiClient1 client1;
    private final GitHubApiClient2 client2;

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return client1.getUser(username);
    }

    @GetMapping("/users/{username}/keys")
    public Key[] getKeys(@PathVariable String username) {
        return client1.getKeys(username);
    }

    @GetMapping("/users/{username}/followers")
    public Follow[] getFollowers(@PathVariable String username) {
        return client2.getFollowers(username);
    }

    @GetMapping("/users/{username}/following")
    public Follow[] getFollowing(@PathVariable String username) {
        return client2.getFollowing(username);
    }
}
