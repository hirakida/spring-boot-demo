package com.example.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GitHubClient {
    private final RestTemplate restTemplate;

    public GitHubClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public User getUser(String username) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com")
                                         .path("/users/{username}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(url, User.class, username);
    }
}
