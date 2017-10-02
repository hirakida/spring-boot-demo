package com.example.client;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class GitHubAsyncApiClient {

    private static final String API_URL = "https://api.github.com";
    private final AsyncRestOperations asyncRestOperations;

    public GitHubUser getUsers(String userName) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/users/{username}")
                                         .buildAndExpand(userName)
                                         .toUriString();
        ListenableFuture<ResponseEntity<GitHubUser>> response =
                asyncRestOperations.getForEntity(url, GitHubUser.class);
        try {
            ResponseEntity<GitHubUser> entity = response.get();
            return entity.getBody();
        } catch (InterruptedException | ExecutionException e) {
            log.error("error", e);
            throw new RestClientException("error");
        }
    }
}
