package com.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        // delete all
        accountRepository.deleteAll();

        // initial data
        IntStream.rangeClosed(1, 6).forEach(i -> {
            String name = "user" + i;
            // findByName
            if (accountRepository.findByName(name).isEmpty()) {
                Account account = new Account();
                account.setName(name);
                accountRepository.save(account);
            }
        });

        // findAll
        List<Account> accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);

        // insert
        Account newAccount = new Account();
        newAccount.setName("user7");
        newAccount = accountRepository.save(newAccount);
        log.info("save: {}", newAccount);

        // update
        newAccount.setName(newAccount.getName() + "__");
        accountRepository.save(newAccount);
        log.info("save: {}", newAccount);

        // findOne
        Account account = accountRepository.findOne(newAccount.getId());
        log.info("findOne: {}", account);

        // delete
        accountRepository.delete(account.getId());

        accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
