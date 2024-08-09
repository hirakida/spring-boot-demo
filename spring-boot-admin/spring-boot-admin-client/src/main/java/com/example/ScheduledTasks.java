package com.example;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final GitHubApiClient gitHubApiClient;

    @Scheduled(fixedDelay = 5000)
    public void task1() {
        log.info("{}", LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 60000)
    public void task2() {
        JsonNode user = gitHubApiClient.getUser("hirakida");
        log.info("user: {}", user);
    }
}
