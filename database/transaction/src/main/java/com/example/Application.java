package com.example;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.core.Account;
import com.example.core.AccountRepository;
import com.example.core.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### start #####");

        accountRepository.deleteAll();

        accountService.create("name1");
        accountService.createTxManager("name2");
        accountService.createTransactionTemplate("name3");
        accountService.deleteTransactionTemplate("name3");

        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> log.info(" {}", account));
        log.info("##### end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
