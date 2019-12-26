package com.example.client;

import java.util.Map;

import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StringRedisClient {
    private final ReactiveStringRedisTemplate redisTemplate;

    public Mono<String> get(String key) {
        return redisTemplate.hasKey(key)
                            .flatMap(hasKey -> hasKey ? redisTemplate.opsForValue().get(key) : Mono.empty());
    }

    public Mono<Boolean> multiSet(Map<String, String> map) {
        return redisTemplate.opsForValue().multiSet(map);
    }
}
