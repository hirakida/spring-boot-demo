package com.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### jpa-hsqldb start #####");

        // delete all
        accountRepository.deleteAll();

        // insert
        IntStream.rangeClosed(1, 6)
                 .forEach(i -> {
                     Account account = new Account();
                     account.setName("user" + i);
                     accountRepository.saveAndFlush(account);
                 });

        // findAll
        List<Account> accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);

        log.info("##### jpa-hsqldb start #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
