package com.example;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableTransactionManagement
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        IntStream.range(0, 5)
                 .forEach(i -> userService.create("name1" + i).block());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
