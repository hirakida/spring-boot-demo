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
public class JdbcApplication implements CommandLineRunner {
    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### start #####");
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> account.setName(account.getName() + '_'));
        accountRepository.batchUpdate(accounts);
        log.info("batchUpdate: {}", accounts);
        log.info("##### end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }
}
