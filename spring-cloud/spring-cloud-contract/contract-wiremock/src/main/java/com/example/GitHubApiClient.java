package com.example;

import org.springframework.lang.Nullable;
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

    @Nullable
    public JsonNode getUser(String username) {
        String uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/users/{username}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, JsonNode.class, username);
    }
}
