package com.example;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.Account;
import com.example.domain.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### jpa-h2 start #####");
        // 定義すれば自動生成されるfindXXXXメソッド
        List<Account> accounts = accountRepository.findByName("user1");
        log.info("findByName: {}", accounts);

        accounts = accountRepository.findByNameLike("user%");
        log.info("findByNameLike: {}", accounts);

        accounts = accountRepository.findByNameStartingWith("user");
        log.info("findByNameStartingWith: {}", accounts);

        accounts = accountRepository.findByNameEndingWith("2");
        log.info("findByNameEndingWith: {}", accounts);

        accounts = accountRepository.findByNameContaining("user");
        log.info("findByNameContaining: {}", accounts);

        accounts = accountRepository.findByIdLessThan(4);
        log.info("findByIdLessThan: {}", accounts);

        accounts = accountRepository.findByIdGreaterThan(4);
        log.info("findByIdGreaterThan: {}", accounts);

        log.info("##### jpa-h2 end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
