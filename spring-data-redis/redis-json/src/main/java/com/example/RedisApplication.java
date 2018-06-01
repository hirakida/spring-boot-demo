package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RedisApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws IOException {

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     final String key = UserRepository.KEY_NAME_BASE + i;
                     User user1 = new User(i, "name" + i, LocalDateTime.now(), LocalDateTime.now());
                     userRepository.set(key, user1, 100);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
