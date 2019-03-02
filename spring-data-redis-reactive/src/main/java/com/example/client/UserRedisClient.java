package com.example.client;

import java.util.Map;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

import com.example.entity.User;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserRedisClient {
    private final ReactiveRedisTemplate<String, User> redisTemplate;

    public Mono<User> get(String key) {
        return redisTemplate.hasKey(key)
                            .flatMap(hasKey -> redisTemplate.opsForValue().get(key));
    }

    public Mono<Boolean> multiSet(Map<String, User> map) {
        return redisTemplate.opsForValue().multiSet(map);
    }
}
