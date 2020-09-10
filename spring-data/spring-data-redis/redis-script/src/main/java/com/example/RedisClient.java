package com.example;

import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisClient {
    private final StringRedisTemplate redisTemplate;
    private final RedisScript<Boolean> redisScriptCheckAndSet;
    private final RedisScript<Boolean> redisScriptExists;

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Boolean checkAndSet(String key, String oldValue, String newValue) {
        return redisTemplate.execute(redisScriptCheckAndSet, List.of(key), oldValue, newValue);
    }

    public Boolean exists(String key, String value) {
        return redisTemplate.execute(redisScriptExists, List.of(key), value);
    }
}
