package com.example;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;

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
        Account account = accountService.create("name1");
        log.info("{}", account);

        account = accountService.create2("name2");
        log.info("{}", account);

        account = accountService.update(account.getId(), "name2_2");
        log.info("{}", account);

        accountService.delete(account.getId());

        List<Account> accounts = accountRepository.findAll();
        log.info("{}", accounts);
        log.info("##### end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
