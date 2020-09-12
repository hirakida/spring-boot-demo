package com.example;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.client.StringRedisClient;
import com.example.client.UserRedisClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationEventListener {
    private final StringRedisClient stringRedisClient;
    private final UserRedisClient userRedisClient;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> stringRedisClient.set("key" + i, "value" + i, 100));
        stringRedisClient.get("key1")
                         .ifPresent(value -> log.info("key1: {}", value));
        stringRedisClient.get("key6")
                         .ifPresent(value -> log.info("key6: {}", value));

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     User user = new User(i, "name" + i, LocalDateTime.now(), LocalDateTime.now());
                     userRedisClient.set(i, user, 100);
                 });
        userRedisClient.get(1)
                       .ifPresent(value -> log.info("{}", value));
    }
}
