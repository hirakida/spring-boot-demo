package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
