package com.example.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(SnakeCaseStrategy.class)
@Value
public class User {
    long id;
    String gravatarId;
    String login;
    String name;
    String avatarUrl;
    String url;
    String htmlUrl;
    String followersUrl;
    String followingUrl;
    String gistsUrl;
    String starredUrl;
    String subscriptionsUrl;
    String organizationsUrl;
    String reposUrl;
    String eventsUrl;
    String receivedEventsUrl;
    String type;
    String company;
    String blog;
    String location;
    String email;
    String bio;
    boolean siteAdmin;
    boolean hireable;
    long publicRepos;
    long publicGists;
    long followers;
    long following;
    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;
}
