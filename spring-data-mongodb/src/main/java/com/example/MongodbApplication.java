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
public class MongodbApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        accountRepository.deleteAll();

        IntStream.rangeClosed(1, 6)
                 .forEach(i -> {
                     String name = "user" + i;
                     Account account = new Account();
                     account.setName(name);
                     accountRepository.save(account);
                 });

        IntStream.rangeClosed(1, 6)
                 .forEach(i -> accountRepository.findByName("user" + i)
                                                .forEach(name -> log.info("{}", name)));
    }

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }
}
