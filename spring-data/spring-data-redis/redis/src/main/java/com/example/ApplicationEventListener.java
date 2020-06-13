package com.example;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.client.StringRedisClient;
import com.example.client.UserRedisClient;
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final StringRedisClient stringRedisClient;
    private final UserRedisClient userRedisClient;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> stringRedisClient.set("key" + i, "value" + i, 100));

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     User user = new User(i, "name" + i, LocalDateTime.now(), LocalDateTime.now());
                     userRedisClient.set(i, user, 100);
                 });
    }
}
