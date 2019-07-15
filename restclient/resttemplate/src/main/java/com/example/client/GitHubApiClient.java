package com.example.client;

import java.util.Optional;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {
    public static final String API_URL = "https://api.github.com";
    private final RestOperations restOperations;

    public User getUser(String username) {
        return Optional.ofNullable(restOperations.getForObject(getUrl(), User.class, username))
                       .orElseThrow();
    }

    public Resource getUserWithResource(String username) {
        return Optional.of(restOperations.exchange(getUrl(), HttpMethod.GET, HttpEntity.EMPTY,
                                                   Resource.class, username))
                       .map(HttpEntity::getBody)
                       .orElseThrow();
    }

    public Set<HttpMethod> options() {
        return restOperations.optionsForAllow(API_URL);
    }

    private static String getUrl() {
        return UriComponentsBuilder.fromHttpUrl(API_URL)
                                   .path("/users/{username}")
                                   .build(false)
                                   .toUriString();
    }
}
