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
    public static final String API_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    public Optional<User> getUser(String username) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/users/{username}")
                                         .build(false)
                                         .toUriString();
        User user = restTemplate.getForObject(url, User.class, username);
        return Optional.ofNullable(user);
    }

    public Optional<JsonNode> getEmojis() {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/emojis")
                                         .toUriString();
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        return Optional.ofNullable(jsonNode);
    }

    public Optional<JsonNode> getEvents() {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/events")
                                         .toUriString();
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        return Optional.ofNullable(jsonNode);
    }

    public Optional<Resource> getFeeds() {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/feeds")
                                         .toUriString();
        Resource resource = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Resource.class)
                                        .getBody();
        return Optional.ofNullable(resource);
    }

    public Set<HttpMethod> options() {
        return restTemplate.optionsForAllow(API_URL);
    }
}
