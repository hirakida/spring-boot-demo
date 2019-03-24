package com.example.client;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Repo;
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GitHubApiClient {
    private final OAuth2RestTemplate restTemplate;

    public User getUser() {
        String uri = UriComponentsBuilder.fromHttpUrl("https://api.github.com/user")
                                         .toUriString();
        return restTemplate.getForObject(uri, User.class);
    }

    public Repo[] getRepos() {
        String uri = UriComponentsBuilder.fromHttpUrl("https://api.github.com/user/repos")
                                         .toUriString();
        return restTemplate.getForObject(uri, Repo[].class);
    }
}
