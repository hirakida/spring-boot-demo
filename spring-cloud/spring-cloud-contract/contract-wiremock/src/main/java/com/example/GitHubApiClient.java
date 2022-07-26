package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {
    static final String BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    public JsonNode getUser(String username) {
        final String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                               .path("/users/{username}")
                                               .build(false)
                                               .toUriString();
        return restTemplate.getForObject(url, JsonNode.class, username);
    }
}
