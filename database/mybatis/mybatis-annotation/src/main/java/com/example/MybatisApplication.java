package com.example;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Account;
import com.example.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class MybatisApplication implements CommandLineRunner {
    private final AccountService accountService;

    @Override
    public void run(String... args) {
        List<Account> accounts = accountService.findAll();
        accounts.forEach(account -> log.info("{}", account));
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
