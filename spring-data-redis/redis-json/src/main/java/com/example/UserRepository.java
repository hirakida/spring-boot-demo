package com.example;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    public static final String KEY_NAME_BASE = "user";
    private final RedisTemplate<String, User> userRedisTemplate;

    public Optional<User> get(long id) {
        return get(KEY_NAME_BASE + id);
    }

    public Optional<User> get(String key) {
        return userRedisTemplate.hasKey(key)
               ? Optional.of(userRedisTemplate.opsForValue().get(key))
               : Optional.empty();
    }

    public void set(long id, User user) {
        String key = KEY_NAME_BASE + id;
        set(key, user);
    }

    public void set(String key, User user) {
        userRedisTemplate.opsForValue()
                            .set(key, user);
    }

    public void set(String key, User user, long seconds) {
        userRedisTemplate.opsForValue()
                            .set(key, user, seconds, TimeUnit.SECONDS);
    }

    public void delete(long id) {
        String key = KEY_NAME_BASE + id;
        if (userRedisTemplate.hasKey(key)) {
            delete(key);
        }
    }

    public void delete(String key) {
        userRedisTemplate.delete(key);
    }
}
