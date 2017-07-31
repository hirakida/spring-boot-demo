package com.example.service;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AccountRepository {

    final RedisTemplate<String, Account> accountRedisTemplate;

    public Optional<Account> findByKey(String key) {
        if (!accountRedisTemplate.hasKey(key)) {
            return Optional.empty();
        }
        ValueOperations<String, Account> operations = accountRedisTemplate.opsForValue();
        return Optional.of(operations.get(key));
    }

    public void save(String key, Account account) {
        ValueOperations<String, Account> operations = accountRedisTemplate.opsForValue();
        operations.set(key, account);
    }

    public void delete(String key) {
        accountRedisTemplate.delete(key);
    }
}
