package com.example;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StringRepository {

    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<Boolean> redisScriptCheckAndSet;
    private final RedisScript<Boolean> redisScriptExists;

    public void set(String key, String value, long seconds) {
        stringRedisTemplate.opsForValue()
                           .set(key, value, seconds, TimeUnit.SECONDS);
    }

    public boolean checkAndSet(String key, String oldValue, String newValue) {
        return stringRedisTemplate.execute(redisScriptCheckAndSet,
                                           Collections.singletonList(key),
                                           oldValue, newValue);
    }

    public boolean exists(String key, String value) {
        return stringRedisTemplate.execute(redisScriptExists,
                                           Collections.singletonList(key),
                                           value);
    }
}
