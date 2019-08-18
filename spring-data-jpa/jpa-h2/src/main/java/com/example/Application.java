package com.example;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("findByName: {}", userRepository.findByName("user1"));
        log.info("findByNameLike: {}", userRepository.findByNameLike("user%"));
        log.info("findByNameStartingWith: {}", userRepository.findByNameStartingWith("user"));
        log.info("findByNameEndingWith: {}", userRepository.findByNameEndingWith("2"));
        log.info("findByNameContaining: {}", userRepository.findByNameContaining("user"));
        log.info("findByIdLessThan: {}", userRepository.findByIdLessThan(4));
        log.info("findByIdGreaterThan: {}", userRepository.findByIdGreaterThan(4));
        log.info("findByEnabledTrue: {}", userRepository.findByEnabledTrue());
        log.info("findByEnabledFalse: {}", userRepository.findByEnabledFalse());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
