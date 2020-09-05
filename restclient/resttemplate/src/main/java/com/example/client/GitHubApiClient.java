package com.example.client;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
    private final RestTemplate restTemplate;

    public User getUser(String username) {
        String url = buildUrl("/users/{username}", false);
        return restTemplate.getForObject(url, User.class, username);
    }

    public List<Key> getKeys(String username) {
        String url = buildUrl("/users/{username}/keys", false);
        Key[] keys = restTemplate.getForObject(url, Key[].class, username);
        return List.of(Objects.requireNonNull(keys));
    }

    public JsonNode getEvents() {
        String url = buildUrl("/events", true);
        return restTemplate.getForObject(url, JsonNode.class);
    }

    public Resource getFeeds() {
        String url = buildUrl("/feeds", true);
        return restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Resource.class)
                           .getBody();
    }

    public Set<HttpMethod> options() {
        return restTemplate.optionsForAllow(BASE_URL);
    }

    private static String buildUrl(String path, boolean encoded) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                   .path(path)
                                   .build(encoded)
                                   .toUriString();
    }
}
