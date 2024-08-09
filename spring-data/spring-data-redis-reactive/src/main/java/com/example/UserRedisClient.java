package com.example;

import java.util.Map;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRedisClient {
    private final ReactiveRedisTemplate<String, User> redisTemplate;

    public Mono<User> get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Mono<Boolean> set(String key, User user) {
        return redisTemplate.opsForValue().set(key, user);
    }

    public Mono<Boolean> multiSet(Map<String, User> map) {
        return redisTemplate.opsForValue().multiSet(map);
    }
}
