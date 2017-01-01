package com.example;

import java.io.IOException;
import java.util.List;

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
        log.info("##### jdbc test start #####");

        // findAll
        List<Account> accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);

        // insert
        Account newAccount = Account.of("user7");
        newAccount = accountRepository.insert(newAccount);
        log.info("insert: {}", newAccount);

        // update
        newAccount.setName(newAccount.getName() + "__");
        accountRepository.update(newAccount);
        log.info("update: {}", newAccount);

        // delete
        accountRepository.delete(newAccount.getId());

        accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);
        log.info("##### jdbc test end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
