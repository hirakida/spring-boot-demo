package com.example;

import java.io.IOException;
import java.util.List;

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
public class Application implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        List<Account> accounts = accountRepository.findByName("user1");
        log.info("findByName: {}", accounts);

        accounts = accountRepository.findByNameLike("user%");
        log.info("findByNameLike: {}", accounts);

        accounts = accountRepository.findByNameStartingWith("user");
        log.info("findByNameStartingWith: {}", accounts);

        accounts = accountRepository.findByNameEndingWith("2");
        log.info("findByNameEndingWith: {}", accounts);

        accounts = accountRepository.findByNameContaining("user");
        log.info("findByNameContaining: {}", accounts);

        accounts = accountRepository.findByIdLessThan(4);
        log.info("findByIdLessThan: {}", accounts);

        accounts = accountRepository.findByIdGreaterThan(4);
        log.info("findByIdGreaterThan: {}", accounts);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
