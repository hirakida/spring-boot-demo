package com.example.client;

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
        return restOperations.getForObject(getUserUrl(), User.class, username);
    }

    public Resource getUserWithResource(String username) {
        return restOperations.exchange(getUserUrl(), HttpMethod.GET, HttpEntity.EMPTY, Resource.class, username)
                             .getBody();
    }

    public Set<HttpMethod> options() {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .toUriString();
        return restOperations.optionsForAllow(url);
    }

    private static String getUserUrl() {
        return UriComponentsBuilder.fromHttpUrl(API_URL)
                                   .path("/users/{username}")
                                   .build(false)
                                   .toUriString();
    }
}
