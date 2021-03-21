package com.example.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.GitHubUser;

@Component
public class GitHubApiClient {
    private final RestTemplate restTemplate;

    public GitHubApiClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public GitHubUser getUser(String username) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com")
                                         .path("/users/{username}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(url, GitHubUser.class, username);
    }
}
