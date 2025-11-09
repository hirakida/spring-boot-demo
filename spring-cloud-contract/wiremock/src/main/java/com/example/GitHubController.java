package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubController {
    private final GitHubApiClient client;

    public GitHubController(GitHubApiClient gitHubApiClient) {
        client = gitHubApiClient;
    }

    @GetMapping("/users/{username}")
    public JsonNode getUser(@PathVariable String username) {
        return client.getUser(username);
    }
}
