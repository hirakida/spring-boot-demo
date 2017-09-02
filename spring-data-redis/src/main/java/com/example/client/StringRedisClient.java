package com.example.client;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class StringRedisClient {

    public static final String KEY_NAME_BASE = "spring-data-redis-string";
    final StringRedisTemplate stringRedisTemplate;

    public Optional<String> get(String key) {
        if (!stringRedisTemplate.hasKey(key)) {
            return Optional.empty();
        }
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        return Optional.of(operations.get(key));
    }

    public Boolean expire(String key, long seconds) {
        return stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public void set(String key, String value) {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(key, value);
    }

    public void set(String key, String value, long seconds) {
        stringRedisTemplate.opsForValue()
                           .set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }
}
