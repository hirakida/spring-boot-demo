package com.example.client;

import java.util.Optional;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    public Optional<User> getUser(String username) {
        User user = restTemplate.getForObject(buildUrl("/users/{username}", false), User.class, username);
        return Optional.ofNullable(user);
    }

    public Optional<JsonNode> getEmojis() {
        JsonNode jsonNode = restTemplate.getForObject(buildUrl("/emojis"), JsonNode.class);
        return Optional.ofNullable(jsonNode);
    }

    public Optional<JsonNode> getEvents() {
        JsonNode jsonNode = restTemplate.getForObject(buildUrl("/events"), JsonNode.class);
        return Optional.ofNullable(jsonNode);
    }

    public Optional<Resource> getFeeds() {
        Resource resource = restTemplate.exchange(buildUrl("/feeds"),
                                                  HttpMethod.GET, HttpEntity.EMPTY, Resource.class)
                                        .getBody();
        return Optional.ofNullable(resource);
    }

    public Set<HttpMethod> options() {
        return restTemplate.optionsForAllow(BASE_URL);
    }

    private static String buildUrl(String path) {
        return buildUrl(path, true);
    }

    private static String buildUrl(String path, boolean encoded) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                   .path(path)
                                   .build(encoded)
                                   .toUriString();
    }
}
