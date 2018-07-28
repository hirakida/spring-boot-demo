package com.example;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisClient {

    private final StringRedisTemplate stringRedisTemplate;

    public Optional<String> get(String key) {
        return stringRedisTemplate.hasKey(key)
               ? Optional.of(stringRedisTemplate.opsForValue().get(key))
               : Optional.empty();
    }

    public void set(String key, String value, long seconds) {
        stringRedisTemplate.opsForValue()
                           .set(key, value, seconds, TimeUnit.SECONDS);
    }
}
