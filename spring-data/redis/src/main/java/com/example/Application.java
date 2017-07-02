package com.example;

import java.io.IOException;
import java.time.LocalDateTime;

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

    final RedisClient redisClient;

    final AccountRepository accountRepository;

    @Override
    public void run(String... strings) throws IOException {

        // String
        {
            final String key1 = "spring.boot.data.redis.data1";
            redisClient.set(key1, "key1_value");
            redisClient.get(key1)
                       .ifPresent(value -> log.info("key={} value={}", key1, value));

            final String key2 = "spring.boot.data.redis.data2";
            redisClient.set(key2, "key2_value");
            redisClient.get(key2)
                       .ifPresent(value -> log.info("key={} value={}", key2, value));

            final String key3 = "spring.boot.data.redis.data3";
            redisClient.get(key3)
                       .ifPresent(value -> log.info("key={} value={}", key3, value));
        }

        // Account
        {
            final String key1 = "spring.boot.data.redis.account1";
            Account account1 = new Account(1, "name1", LocalDateTime.now(), LocalDateTime.now());
            accountRepository.save(key1, account1);
            accountRepository.findByKey(key1)
                             .ifPresent(value -> log.info("key={} value={}", key1, value));

            final String key2 = "spring.boot.data.redis.account2";
            Account account2 = new Account(2, "name2", LocalDateTime.now(), LocalDateTime.now());
            accountRepository.save(key2, account2);
            accountRepository.findByKey(key2)
                             .ifPresent(value -> log.info("key={} value={}", key2, value));
            accountRepository.delete(key2);

            final String key3 = "spring.boot.data.redis.account3";
            accountRepository.findByKey(key3)
                             .ifPresent(value -> log.info("key={} value={}", key3, value));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
