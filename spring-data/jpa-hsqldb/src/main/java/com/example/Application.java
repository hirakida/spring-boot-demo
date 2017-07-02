package com.example;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing
@AllArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {
        log.info("##### jpa-hsqldb start #####");

        accountRepository.deleteAll();

        List<Account> accounts = IntStream.rangeClosed(1, 6)
                                          .mapToObj(i -> {
                                              Account account = new Account();
                                              account.setName("user" + i);
                                              return account;
                                          }).collect(Collectors.toList());
        accountRepository.save(accounts);

        accounts = accountRepository.findAll();
        accounts.forEach(account -> log.info("{}", account));
        log.info("##### jpa-hsqldb end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
