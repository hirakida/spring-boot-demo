package com.example;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.client.AccountRedisClient;
import com.example.client.StringRedisClient;
import com.example.entity.Account;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    final StringRedisClient stringRedisClient;

    final AccountRedisClient accountRedisClient;

    @Override
    public void run(String... strings) throws IOException {

        // String
        {
            final String key1 = StringRedisClient.KEY_NAME_BASE + 1;
            stringRedisClient.set(key1, "key1_value", 100);
            stringRedisClient.get(key1)
                             .ifPresent(value -> log.info("key={} value={}", key1, value));

            final String key2 = StringRedisClient.KEY_NAME_BASE + 2;
            stringRedisClient.set(key2, "key2_value");
            stringRedisClient.get(key2)
                             .ifPresent(value -> log.info("key={} value={}", key2, value));

            final String key3 = StringRedisClient.KEY_NAME_BASE + 3;
            stringRedisClient.get(key3)
                             .ifPresent(value -> log.info("key={} value={}", key3, value));
        }

        // Account
        {
            final String key1 = AccountRedisClient.KEY_NAME_BASE + 1;
            Account account1 = new Account(1, "name1", LocalDateTime.now(), LocalDateTime.now());
            accountRedisClient.set(key1, account1, 100);
            accountRedisClient.get(key1)
                              .ifPresent(value -> log.info("key={} value={}", key1, value));

            final String key2 = AccountRedisClient.KEY_NAME_BASE + 2;
            Account account2 = new Account(2, "name2", LocalDateTime.now(), LocalDateTime.now());
            accountRedisClient.set(key2, account2);
            accountRedisClient.get(key2)
                              .ifPresent(value -> log.info("key={} value={}", key2, value));
            accountRedisClient.delete(key2);

            final String key3 = AccountRedisClient.KEY_NAME_BASE + 3;
            accountRedisClient.get(key3)
                              .ifPresent(value -> log.info("key={} value={}", key3, value));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
