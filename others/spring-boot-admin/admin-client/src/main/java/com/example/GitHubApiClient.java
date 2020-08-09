package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

@Component
@CacheConfig(cacheNames = "cache1")
public class GitHubApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private final RestTemplate restTemplate;

    public GitHubApiClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @Cacheable(key = "'user:' + #username")
    public JsonNode getUser(String username) {
        String uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/users/{username}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, JsonNode.class, username);
    }
}
