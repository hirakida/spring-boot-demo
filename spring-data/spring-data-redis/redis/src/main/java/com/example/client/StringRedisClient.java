package com.example.client;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StringRedisClient {
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<Boolean> redisScriptCheckAndSet;
    private final RedisScript<Boolean> redisScriptExists;

    public Optional<String> get(String key) {
        return Optional.ofNullable(stringRedisTemplate.opsForValue().get(key));
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue()
                           .set(key, value);
    }

    public void set(String key, String value, long seconds) {
        stringRedisTemplate.opsForValue()
                           .set(key, value, seconds, TimeUnit.SECONDS);
    }

    public Boolean expire(String key, long seconds) {
        return stringRedisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public Boolean checkAndSet(String key, String oldValue, String newValue) {
        return stringRedisTemplate.execute(redisScriptCheckAndSet, List.of(key), oldValue, newValue);
    }

    public Boolean exists(String key, String value) {
        return stringRedisTemplate.execute(redisScriptExists, List.of(key), value);
    }
}
