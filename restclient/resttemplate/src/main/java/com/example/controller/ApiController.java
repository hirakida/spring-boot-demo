package com.example.controller;

import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.client.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final GitHubApiClient gitHubApiClient;

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return gitHubApiClient.getUser(username);
    }

    @GetMapping("/users/{username}/resource")
    public Resource getUserWithResource(@PathVariable String username) {
        return gitHubApiClient.getUserWithResource(username);
    }

    @GetMapping("/options")
    public Set<HttpMethod> options() {
        return gitHubApiClient.options();
    }
}
