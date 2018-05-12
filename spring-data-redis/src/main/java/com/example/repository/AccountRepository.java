package com.example.repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    public static final String KEY_NAME_BASE = "redis-account";
    private final RedisTemplate<String, Account> accountRedisTemplate;

    public Optional<Account> get(long id) {
        return get(KEY_NAME_BASE + id);
    }

    public Optional<Account> get(String key) {
        return accountRedisTemplate.hasKey(key)
               ? Optional.of(accountRedisTemplate.opsForValue().get(key))
               : Optional.empty();
    }

    public void set(long id, Account account) {
        String key = KEY_NAME_BASE + id;
        set(key, account);
    }

    public void set(String key, Account account) {
        accountRedisTemplate.opsForValue()
                            .set(key, account);
    }

    public void set(String key, Account account, long seconds) {
        accountRedisTemplate.opsForValue()
                            .set(key, account, seconds, TimeUnit.SECONDS);
    }

    public void delete(long id) {
        String key = KEY_NAME_BASE + id;
        if (accountRedisTemplate.hasKey(key)) {
            delete(key);
        }
    }

    public void delete(String key) {
        accountRedisTemplate.delete(key);
    }
}
