package com.example.client;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StringRedisClient {
    private final StringRedisTemplate redisTemplate;

    public Optional<String> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public Boolean expire(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
