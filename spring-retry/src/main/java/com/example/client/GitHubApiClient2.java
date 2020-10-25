package com.example.client;

import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Follow;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GitHubApiClient2 {
    private static final String BASE_URL = "https://api.github.com";
    private static final Follow[] EMPTY_FOLLOWS = {};
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate1;
    private final RetryTemplate retryTemplate2;

    public GitHubApiClient2(RestTemplateBuilder builder, RetryTemplate retryTemplate1,
                            RetryTemplate retryTemplate2) {
        restTemplate = builder.build();
        this.retryTemplate1 = retryTemplate1;
        this.retryTemplate2 = retryTemplate2;
    }

    public Follow[] getFollowers(String username) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/users/{username}/followers")
                                         .build(false)
                                         .toUriString();
        return retryTemplate1.execute(
                context -> {
                    log.info("retryCallback start [getFollowers] {}", context);
                    Follow[] follows = restTemplate.getForObject(url, Follow[].class, username);
                    log.info("retryCallback end [getFollowers] {}", Arrays.toString(follows));
                    return follows;
                },
                context -> {
                    log.info("recoveryCallback [getFollowers] {}", context);
                    return EMPTY_FOLLOWS;
                });
    }

    public Follow[] getFollowing(String username) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/users/{username}/following")
                                         .build(false)
                                         .toUriString();
        return retryTemplate2.execute(
                context -> restTemplate.getForObject(url, Follow[].class, username),
                context -> EMPTY_FOLLOWS);
    }
}
