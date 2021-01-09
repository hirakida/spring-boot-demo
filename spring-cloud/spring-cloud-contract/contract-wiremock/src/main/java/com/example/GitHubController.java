package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubApiClient client;

    @GetMapping("/users/{username}")
    public JsonNode getUser(@PathVariable String username) {
        return client.getUser(username);
    }
}
