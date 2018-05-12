package com.example;

import java.io.IOException;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        // initial data
        IntStream.rangeClosed(1, 5).forEach(i -> {
            String name = "user" + i;
            if (accountRepository.findByName(name).isEmpty()) {
                Account account = new Account();
                account.setName(name);
                accountRepository.save(account);
            }
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
