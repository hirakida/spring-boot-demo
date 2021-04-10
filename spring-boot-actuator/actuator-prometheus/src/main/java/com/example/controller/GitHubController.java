package com.example.controller;

import java.time.ZonedDateTime;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@RestController
@RequestMapping("/github")
public class GitHubController {
    private final RestTemplate restTemplate;
    private final String url;

    public GitHubController(RestTemplateBuilder builder) {
        restTemplate = builder.build();
        url = UriComponentsBuilder.fromHttpUrl("https://api.github.com")
                                  .path("/users/{username}")
                                  .build(false)
                                  .toUriString();
    }

    @GetMapping("/{username}")
    public GitHubUser getUser(@PathVariable String username) {
        return restTemplate.getForObject(url, GitHubUser.class, username);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(SnakeCaseStrategy.class)
    @Data
    private static class GitHubUser {
        private long id;
        private String login;
        private String name;
        private String url;
    }
}
