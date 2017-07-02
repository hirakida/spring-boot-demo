package com.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    final AccountMapper accountMapper;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### mybatis-h2 start #####");

        // delete all
        accountMapper.deleteAll();

        // insert
        IntStream.rangeClosed(1, 6)
                 .forEach(i -> {
                     Account account = new Account();
                     account.setName("user" + i);
                     accountMapper.insert(account);
                 });

        Account account = accountMapper.findByName("user1");
        log.info("findByName: {}", account);
        if (account != null) {
            account.setName("user11");
            accountMapper.update(account);
            log.info("update: {}", account);
        }

        // findAll
        List<Account> accounts = accountMapper.findAll();
        log.info("findAll: {}", accounts);

        log.info("##### mybatis-h2 end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
