package com.example;

import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Follower;
import com.example.model.Key;
import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GitHubApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private static final Key[] EMPTY_KEYS = {};
    private static final Follower[] EMPTY_FOLLOWERS = {};
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    public GitHubApiClient(RestTemplateBuilder builder, RetryTemplate retryTemplate) {
        restTemplate = builder.build();
        this.retryTemplate = retryTemplate;
    }

    @Retryable(value = { ResourceAccessException.class, HttpServerErrorException.class },
            maxAttempts = 4,
            backoff = @Backoff(delay = 500))
    public User getUser(String username) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/users/{username}")
                                         .build(false)
                                         .toUriString();
        log.info("@Retryable start [getUser]");
        User user = restTemplate.getForObject(url, User.class, username);
        log.info("@Retryable end [getUser]");
        return user;
    }

    @Recover
    public User getUserRecover() {
        log.info("@Recover [getUser]");
        return new User();
    }

    @Retryable(exceptionExpression = "#{@exceptionChecker.shouldRetry(#root)}",
            maxAttemptsExpression = "#{${retry.max-attempt}}",
            backoff = @Backoff(delayExpression = "#{${retry.delay}}"))
    public Key[] getKeys(String username) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/users/{username}/keys")
                                         .build(false)
                                         .toUriString();
        log.info("@Retryable start [getKeys]");
        Key[] keys = restTemplate.getForObject(url, Key[].class, username);
        log.info("@Retryable end [getKeys]");
        return keys;
    }

    @Recover
    public Key[] getKeysRecover() {
        log.info("@Recover [getKeys]");
        return EMPTY_KEYS;
    }

    public Follower[] getFollowers(String username) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/users/{username}/followers")
                                         .build(false)
                                         .toUriString();
        return retryTemplate.execute(
                context -> {
                    log.info("retryCallback start [getFollowers] {}", context);
                    Follower[] followers = restTemplate.getForObject(url, Follower[].class, username);
                    log.info("retryCallback end [getFollowers] {}", Arrays.toString(followers));
                    return followers;
                },
                context -> {
                    log.info("recoveryCallback [getFollowers] {}", context);
                    return EMPTY_FOLLOWERS;
                });
    }
}
