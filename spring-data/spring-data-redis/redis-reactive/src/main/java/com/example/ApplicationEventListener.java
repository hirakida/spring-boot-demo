package com.example;

import static java.util.stream.Collectors.toMap;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;
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
        Map<String, String> strings = IntStream.rangeClosed(1, 10)
                                               .boxed()
                                               .collect(toMap(i -> "key" + i, i -> "value" + i));
        stringRedisClient.multiSet(strings)
                         .flatMap(result -> stringRedisClient.get("key1"))
                         .doOnNext(result -> log.info("key1: {}", result))
                         .flatMap(result -> stringRedisClient.get("key10"))
                         .doOnNext(result -> log.info("key10: {}", result))
                         .subscribe();

        Map<String, User> users =
                IntStream.rangeClosed(1, 5)
                         .mapToObj(i -> new User(i, "user" + i, LocalDateTime.now(), LocalDateTime.now()))
                         .collect(toMap(User::getName, Function.identity()));
        userRedisClient.multiSet(users)
                       .flatMap(result -> userRedisClient.get("user1"))
                       .subscribe(result -> log.info("{}", result));
    }
}
