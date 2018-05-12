package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.StringRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class RedisApplication implements CommandLineRunner {

    private final StringRepository stringRepository;
    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     String key = StringRepository.KEY_NAME_BASE + i;
                     stringRepository.set(key, "value" + i, 100);
                 });
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     String key = StringRepository.KEY_NAME_BASE + i;
                     stringRepository.get(key)
                                     .ifPresent(value -> log.info("key={} value={}", key, value));
                 });

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     final String key = AccountRepository.KEY_NAME_BASE + i;
                     Account account1 = new Account(i, "name" + i, LocalDateTime.now(), LocalDateTime.now());
                     accountRepository.set(key, account1, 100);
                 });
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     final String key = AccountRepository.KEY_NAME_BASE + i;
                     accountRepository.get(key)
                                      .ifPresent(value -> log.info("key={} value={}", key, value));
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
