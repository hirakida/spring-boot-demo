package com.example;

import java.io.IOException;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class RedisApplication implements CommandLineRunner {

    private final StringRepository stringRepository;

    @Override
    public void run(String... strings) throws IOException {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     stringRepository.set("key" + i, "value" + i, 100);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
