package com.example;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class DataJpaApplication implements CommandLineRunner {
    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("findByName: {}", accountRepository.findByName("user1"));
        log.info("findByNameLike: {}", accountRepository.findByNameLike("user%"));
        log.info("findByNameStartingWith: {}", accountRepository.findByNameStartingWith("user"));
        log.info("findByNameEndingWith: {}", accountRepository.findByNameEndingWith("2"));
        log.info("findByNameContaining: {}", accountRepository.findByNameContaining("user"));
        log.info("findByIdLessThan: {}", accountRepository.findByIdLessThan(4));
        log.info("findByIdGreaterThan: {}", accountRepository.findByIdGreaterThan(4));
        log.info("findByEnabledTrue: {}", accountRepository.findByEnabledTrue());
        log.info("findByEnabledFalse: {}", accountRepository.findByEnabledFalse());
    }

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
    }
}
