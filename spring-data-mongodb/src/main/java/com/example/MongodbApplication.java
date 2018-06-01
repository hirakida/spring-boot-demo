package com.example;

import java.io.IOException;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableMongoAuditing
@RequiredArgsConstructor
public class MongodbApplication implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        IntStream.rangeClosed(1, 6)
                 .forEach(i -> {
                     Account account = new Account();
                     account.setName("user" + i);
                     accountRepository.save(account);
                 });
    }

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }
}
