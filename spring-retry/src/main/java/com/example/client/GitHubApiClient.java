package com.example.client;

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

import com.example.model.Follow;
import com.example.model.Key;
import com.example.model.User;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GitHubApiClient {
    private static final String BASE_URL = "https://api.github.com";
    private static final Key[] EMPTY_KEYS = {};
    private static final Follow[] EMPTY_FOLLOWS = {};
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate1;
    private final RetryTemplate retryTemplate2;

    public GitHubApiClient(RestTemplateBuilder builder,
                           RetryTemplate retryTemplate1,
                           RetryTemplate retryTemplate2) {
        restTemplate = builder.build();
        this.retryTemplate1 = retryTemplate1;
        this.retryTemplate2 = retryTemplate2;
    }

    @Retryable(value = { ResourceAccessException.class, HttpServerErrorException.class },
            maxAttempts = 4,
            backoff = @Backoff(delay = 500))
    public User getUser(String username) {
        String url = buildUrl("/users/{username}");
        return restTemplate.getForObject(url, User.class, username);
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
        String url = buildUrl("/users/{username}/keys");
        return restTemplate.getForObject(url, Key[].class, username);
    }

    @Recover
    public Key[] getKeysRecover() {
        log.info("@Recover [getKeys]");
        return EMPTY_KEYS;
    }

    public Follow[] getFollowers(String username) {
        String url = buildUrl("/users/{username}/followers");
        return retryTemplate1.execute(
                context -> restTemplate.getForObject(url, Follow[].class, username),
                context -> EMPTY_FOLLOWS);
    }

    public Follow[] getFollowing(String username) {
        String url = buildUrl("/users/{username}/following");
        return retryTemplate2.execute(
                context -> restTemplate.getForObject(url, Follow[].class, username),
                context -> EMPTY_FOLLOWS);
    }

    private static String buildUrl(String path) {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                   .path(path)
                                   .build(false)
                                   .toUriString();
    }
}
