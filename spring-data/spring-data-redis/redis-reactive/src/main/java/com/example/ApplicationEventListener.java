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
import com.example.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicationEventListener {
    private final StringRedisClient stringRedisClient;
    private final UserRedisClient userRedisClient;

    @EventListener(ApplicationReadyEvent.class)
    public void readyEvent() {
        Map<String, String> strings = IntStream.rangeClosed(1, 10)
                                               .boxed()
                                               .collect(toMap(i -> "key" + i, i -> "value" + i));
        stringRedisClient.multiSet(strings).block();

        Map<String, User> users =
                IntStream.rangeClosed(1, 5)
                         .mapToObj(i -> new User(i, "user" + i, LocalDateTime.now(), LocalDateTime.now()))
                         .collect(toMap(User::getName, Function.identity()));
        userRedisClient.multiSet(users).block();
    }
}
