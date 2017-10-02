package com.example.client;

import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class GitHubApiClient {

    private static final String API_URL = "https://api.github.com";
    private final RestOperations restOperations;

    public GitHubUser getUsers(String userName) {
        return restOperations.getForObject(getUsersUrl(userName), GitHubUser.class);
    }

    public Resource getUsersWithResource(String userName) {
        String url = getUsersUrl(userName);
        ResponseEntity<Resource> response = restOperations.exchange(url, HttpMethod.GET,
                                                                    HttpEntity.EMPTY, Resource.class);
        return response.getBody();
    }

    public Set<HttpMethod> options() {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .toUriString();
        return restOperations.optionsForAllow(url);
    }

    private static String getUsersUrl(String userName) {
        return UriComponentsBuilder.fromHttpUrl(API_URL)
                                   .path("/users/{username}")
                                   .buildAndExpand(userName)
                                   .toUriString();
    }
}
