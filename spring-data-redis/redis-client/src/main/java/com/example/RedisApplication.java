package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.client.StringRedisClient;
import com.example.client.UserRedisClient;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class RedisApplication implements CommandLineRunner {
    private final StringRedisClient stringRedisClient;
    private final UserRedisClient userRedisClient;

    @Override
    public void run(String... strings) throws IOException {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> stringRedisClient.set("key" + i, "value" + i, 100));

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     User user = new User(i, "name" + i, LocalDateTime.now(), LocalDateTime.now());
                     userRedisClient.set(i, user, 100);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
