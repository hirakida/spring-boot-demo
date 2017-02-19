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
public class App implements CommandLineRunner {

    final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### jpa-h2 start #####");

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
        accounts.forEach(account -> log.info(" {}", account));

        accounts.stream()
                .map(Account::getId)
                .forEach(id -> {
                    // fineOneはentityがない場合はnullが返る
                    Account account1 = accountRepository.findOne(id);
                    log.info("findOne: {}", account1);

                    // getOneはentityがない場合はLazyInitializationExceptionがスローされる
                    Account account2 = accountRepository.getOne(id);
//                    log.info("getOne: {}", account2);

                    // update
                    account1.setName(account1.getName() + "__");
                    accountRepository.saveAndFlush(account1);
                    log.info("save: {}", account1);
                });

        // 定義すれば自動生成されるfindXXXXメソッド
        accounts = accountRepository.findByName("user1");
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
        SpringApplication.run(App.class, args);
    }
}
