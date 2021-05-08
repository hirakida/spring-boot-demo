package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.repository.AccountRepository;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        accountRepository.findAll()
                         .forEach(account -> log.info("{}", account));
        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
