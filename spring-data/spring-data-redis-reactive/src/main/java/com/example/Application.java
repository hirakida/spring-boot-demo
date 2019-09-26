package com.example;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.client.StringRedisClient;
import com.example.client.UserRedisClient;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final StringRedisClient stringRedisClient;
    private final UserRedisClient userRedisClient;

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> strings =
                IntStream.rangeClosed(1, 10)
                         .boxed()
                         .collect(Collectors.toMap(i -> "key" + i, i -> "value" + i));
        stringRedisClient.multiSet(strings).block();

        Map<String, User> users =
                IntStream.rangeClosed(1, 5)
                         .mapToObj(i -> new User(i, "user" + i, LocalDateTime.now(), LocalDateTime.now()))
                         .collect(Collectors.toMap(User::getName, Function.identity()));
        userRedisClient.multiSet(users).block();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
