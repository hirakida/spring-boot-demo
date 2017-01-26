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
public class App implements CommandLineRunner {

    final AccountRepository accountRepository;

    final AccountService accountService;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### start #####");

        accountRepository.deleteAll();

        accountService.create("name1");
        accountService.createUseTxManager("name2");
        accountService.createUseTransactionTemplate("name3");

        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> log.info(" {}", account));
        log.info("##### end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
