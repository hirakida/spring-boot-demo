package com.example;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Key;
import com.example.model.User;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private static final ParameterizedTypeReference<List<Key>> KEYS_TYPE =
            new ParameterizedTypeReference<>() {};
    private final RestTemplate restTemplate;

    public User getUser(String username) {
        String url = buildUrl("/users/{username}", false);
        return restTemplate.getForObject(url, User.class, username);
    }

    public List<Key> getKeys(String username) {
        String url = buildUrl("/users/{username}/keys", false);
        ResponseEntity<List<Key>> response =
                restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, KEYS_TYPE, username);
        return response.getBody();
    }

    public JsonNode getEvents() {
        String url = buildUrl("/events", true);
        return restTemplate.getForObject(url, JsonNode.class);
    }

    public JsonNode getFeeds() {
        String url = buildUrl("/feeds", true);
        return restTemplate.getForObject(url, JsonNode.class);
    }

    private static String buildUrl(String path, boolean encoded) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                   .path(path)
                                   .build(encoded)
                                   .toUriString();
    }
}
