package com.example.client;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class GitHubApiClient {

    private static final String API_URL = "https://api.github.com";

    final RestOperations restOperations;

    public GitHubUser getUsers(String userName) {
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                                         .path("/users/{username}")
                                         .buildAndExpand(userName)
                                         .toUriString();
        return restOperations.getForObject(url, GitHubUser.class);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(SnakeCaseStrategy.class)
    @Data
    @SuppressWarnings("serial")
    public static class GitHubUser implements Serializable {
        private long id;
        private String gravatarId;
        private String login;
        private String name;
        private String avatarUrl;
        private String url;
        private String htmlUrl;
        private String followersUrl;
        private String followingUrl;
        private String gistsUrl;
        private String starredUrl;
        private String subscriptionsUrl;
        private String organizationsUrl;
        private String reposUrl;
        private String eventsUrl;
        private String receivedEventsUrl;
        private String type;
        private String company;
        private String blog;
        private String location;
        private String email;
        private String bio;
        private boolean siteAdmin;
        private boolean hireable;
        private long publicRepos;
        private long publicGists;
        private long followers;
        private long following;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
    }
}
