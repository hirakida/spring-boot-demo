package com.example;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.example.helper.UrlBuilder;
import com.example.model.GitHubUser;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {

    private final RestOperations restOperations;

    public GitHubUser getUsers(String userName) {
        String url = UrlBuilder.getUsersUrl(userName);
        return restOperations.getForObject(url, GitHubUser.class);
    }
}
