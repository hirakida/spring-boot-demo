package com.example;

import java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RedisClient {

    final StringRedisTemplate stringRedisTemplate;

    public Optional<String> get(String key) {
        if (!stringRedisTemplate.hasKey(key)) {
            return Optional.empty();
        }
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        return Optional.of(operations.get(key));
    }

    public void set(String key, String value) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(key, value);
    }
}
