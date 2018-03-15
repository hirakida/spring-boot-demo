package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.core.Account;
import com.example.core.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Doma2Application implements CommandLineRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        // use SelectType.STREAM
        List<Account> accounts = accountRepository.findAll(stream -> stream.collect(Collectors.toList()));
        accounts.forEach(account -> log.info("{}", account));

        // use SelectType.COLLECT
        accounts = accountRepository.findAll(Collectors.toList());
        accounts.forEach(account -> log.info("{}", account));

        Map<Long, List<Account>> accountMap = accountRepository.findAll(Collectors.groupingBy(Account::getId));
        accountMap.forEach((key, value) -> log.info("key={} value={}", key, value));
    }

    public static void main(String[] args) {
        SpringApplication.run(Doma2Application.class, args);
    }
}
