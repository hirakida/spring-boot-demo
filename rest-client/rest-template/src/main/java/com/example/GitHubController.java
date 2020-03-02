package com.example;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.model.User;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class GitHubController {
    private final GitHubApiClient gitHubApiClient;

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return gitHubApiClient.getUser(username)
                              .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @GetMapping("/events")
    public JsonNode getEvents() {
        return gitHubApiClient.getEvents()
                              .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @GetMapping("/feeds")
    public Resource getFeeds() {
        return gitHubApiClient.getFeeds()
                              .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @GetMapping("/options")
    public Set<HttpMethod> options() {
        return gitHubApiClient.options();
    }
}
