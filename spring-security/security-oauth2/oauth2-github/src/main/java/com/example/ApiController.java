package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.GitHubApiClient;
import com.example.model.Repo;
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ApiController {
    private final OAuth2RestTemplate restTemplate;
    private final GitHubApiClient gitHubApiClient;

    @GetMapping("/")
    public OAuth2Authentication index(@AuthenticationPrincipal OAuth2Authentication authentication) {
        return authentication;
    }

    @GetMapping("/access_token")
    public OAuth2AccessToken getAccessToken() {
        return restTemplate.getAccessToken();
    }

    @GetMapping("/resource")
    public OAuth2ProtectedResourceDetails getResource() {
        return restTemplate.getResource();
    }

    @GetMapping("/user")
    public User getUser() {
        return gitHubApiClient.getUser();
    }

    @GetMapping("/repos")
    public Repo[] getRepos() {
        return gitHubApiClient.getRepos();
    }
}
