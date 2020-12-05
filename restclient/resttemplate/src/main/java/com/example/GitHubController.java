package com.example;

import java.util.List;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.client.GitHubApiClient;
import com.example.model.Key;
import com.example.model.User;
import com.fasterxml.jackson.databind.JsonNode;

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
    public List<Key> getKeys(@PathVariable String username) {
        return client.getKeys(username);
    }

    @GetMapping("/events")
    public JsonNode getEvents() {
        return client.getEvents();
    }

    @GetMapping("/feeds")
    public Resource getFeeds() {
        return client.getFeeds();
    }

    @GetMapping("/options")
    public Set<HttpMethod> options() {
        return client.options();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Void> handleException(HttpClientErrorException e) {
        return ResponseEntity.status(e.getStatusCode()).build();
    }
}
