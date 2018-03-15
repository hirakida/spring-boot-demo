package com.example;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### start #####");

        List<Account> accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);

        Account newAccount = new Account();
        newAccount.setName("user6");
        newAccount = accountRepository.insert(newAccount);
        log.info("insert: {}", newAccount);

        newAccount.setName(newAccount.getName() + "__");
        accountRepository.update(newAccount);
        log.info("update: {}", newAccount);

        accountRepository.delete(newAccount.getId());

        accounts.forEach(account -> account.setName(account.getName() + '_'));
        accountRepository.batchUpdate(accounts);
        log.info("batchUpdate: {}", accounts);

        accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);
        log.info("##### end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
