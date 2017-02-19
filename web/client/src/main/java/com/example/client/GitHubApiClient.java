package com.example.client;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GitHubApiClient {

    private static final String API_URL = "https://api.github.com";
    private static final String USERS_PATH = "/users/{username}";

    @Autowired
    RestOperations restOperations;

    @Autowired
    AsyncRestOperations asyncRestOperations;

    /**
     * https://developer.github.com/v3/users/
     */
    public GitHubUser getUsers(String userName) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path(USERS_PATH)
                                         .buildAndExpand(userName)
                                         .toUriString();
        return restOperations.getForObject(url, GitHubUser.class);
    }

    public GitHubUser getUsersWithAsync(String userName) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path(USERS_PATH)
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

    public Set<HttpMethod> options() {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .toUriString();
        return restOperations.optionsForAllow(url);
    }
}
