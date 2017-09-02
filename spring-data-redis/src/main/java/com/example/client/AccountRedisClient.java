package com.example.client;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AccountRedisClient {

    public static final String KEY_NAME_BASE = "spring-data-redis-account";
    final RedisTemplate<String, Account> accountRedisTemplate;

    public Optional<Account> get(long id) {
        return get(KEY_NAME_BASE + id);
    }

    public Optional<Account> get(String key) {
        if (!accountRedisTemplate.hasKey(key)) {
            return Optional.empty();
        }
        return Optional.of(accountRedisTemplate.opsForValue().get(key));
    }

    public void set(String key, Account account) {
        accountRedisTemplate.opsForValue()
                            .set(key, account);
    }

    public void set(String key, Account account, long seconds) {
        accountRedisTemplate.opsForValue()
                            .set(key, account, seconds, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        accountRedisTemplate.delete(key);
    }
}
