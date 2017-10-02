package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GitHubApiClient {

    private static final String API_URL = "https://api.github.com";

    final RestOperations restOperations;

    public GitHubUser getUsers(String userName) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/users/{username}")
                                         .buildAndExpand(userName)
                                         .toUriString();
        return restOperations.getForObject(url, GitHubUser.class);
    }
}
