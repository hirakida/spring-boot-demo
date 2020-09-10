package com.example;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final RedisClient redisClient;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        redisClient.set("key1", "old-value");
        log.info("get: {}", redisClient.get("key1"));

        boolean result = redisClient.exists("key1", "old-value");
        log.info("exists: {}", result);

        result = redisClient.checkAndSet("key1", "old-value", "new-value");
        log.info("checkAndSet: {}", result);
        log.info("get: {}", redisClient.get("key1"));
    }
}
