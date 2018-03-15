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

        accountRepository.deleteAll();

        // initial data
        IntStream.rangeClosed(1, 6).forEach(i -> {
            String name = "user" + i;
            if (accountRepository.findByName(name).isEmpty()) {
                Account account = new Account();
                account.setName(name);
                accountRepository.save(account);
            }
        });

        accountRepository.findAll()
                         .forEach(account -> log.info("{}", account));

        Account newAccount = new Account();
        newAccount.setName("user7");
        newAccount = accountRepository.save(newAccount);
        log.info("{}", newAccount);

        newAccount.setName(newAccount.getName() + "__");
        accountRepository.save(newAccount);
        log.info("{}", newAccount);

        Account account = accountRepository.findOne(newAccount.getId());
        accountRepository.delete(account.getId());
    }

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
