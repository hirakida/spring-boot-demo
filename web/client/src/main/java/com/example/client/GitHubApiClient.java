package com.example.client;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GitHubApiClient {

    private static final String API_URL = "https://api.github.com";
    private static final String USERS_PATH = "/users/{username}";

    @Autowired
    RestOperations restOperations;

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

    public Set<HttpMethod> options() {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .toUriString();
        return restOperations.optionsForAllow(url);
    }
}
