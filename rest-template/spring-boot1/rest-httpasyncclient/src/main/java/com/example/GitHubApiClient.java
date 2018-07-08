package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {

    public static final String API_URL = "https://api.github.com";
    private final AsyncRestOperations asyncRestOperations;

    public ListenableFuture<ResponseEntity<GitHubUser>> getUsers(String userName) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/users/{username}")
                                         .buildAndExpand(userName)
                                         .toUriString();
        return asyncRestOperations.getForEntity(url, GitHubUser.class);
    }
}
