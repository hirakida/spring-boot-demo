package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Follow;
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GitHubApiController {
    private final GitHubApiClient client;

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return client.getUser(username);
    }

    @GetMapping("/users/{username}/following")
    public List<Follow> getFollowing(@PathVariable String username) {
        return client.getUserFollowing(username);
    }
}
