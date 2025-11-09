package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GitHubApiClient {
    static final String BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    public GitHubApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode getUser(String username) {
        final String url = UriComponentsBuilder.fromUriString(BASE_URL)
                .path("/users/{username}")
                .build(false)
                .toUriString();
        return restTemplate.getForObject(url, JsonNode.class, username);
    }
}
