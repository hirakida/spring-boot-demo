package com.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        // delete all
        accountRepository.deleteAll();

        // insert
        IntStream.rangeClosed(1, 6)
                 .forEach(i -> {
                     Account account = new Account();
                     account.setName("user" + i);
                     accountRepository.saveAndFlush(account);
                 });

        // findAll
        List<Account> accounts = accountRepository.findAll();
        log.info("findAll: {}", accounts);

        accounts.stream()
                .map(Account::getId)
                .forEach(id -> {
                    // fineOneはentityがない場合はnullが返る
                    Account account1 = accountRepository.findOne(id);
                    log.info("findOne: {}", account1);

                    // getOneはentityがない場合はLazyInitializationExceptionがスローされる
                    Account account2 = accountRepository.getOne(id);
//                    log.info("getOne: {}", account2);
                });

        accounts = accountRepository.findByName("user1");
        log.info("findByName: {}", accounts);
        if (!accounts.isEmpty()) {
            // update
            Account account = accounts.get(0);
            account.setName(account.getName() + "__");
            accountRepository.saveAndFlush(account);
            log.info("save: {}", account);
        }

        // 定義すれば自動生成されるfindXXXXメソッド
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
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
