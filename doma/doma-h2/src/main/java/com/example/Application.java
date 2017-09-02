package com.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### doma-h2 start #####");
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> log.info("{}", account));

        // use SelectType.STREAM
        accounts = accountRepository.findAll(stream -> stream.collect(Collectors.toList()));
        accounts.forEach(account -> log.info("{}", account));

        // use SelectType.COLLECT
        accounts = accountRepository.findAll(Collectors.toList());
        accounts.forEach(account -> log.info("{}", account));

        Map<Long, List<Account>> accountMap = accountRepository.findAll(Collectors.groupingBy(Account::getId));
        accountMap.forEach((key, value) -> log.info("key={} value={}", key, value));
        log.info("##### doma-h2 end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
