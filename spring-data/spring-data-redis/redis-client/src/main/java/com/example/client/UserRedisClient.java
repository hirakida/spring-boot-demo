package com.example.client;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRedisClient {
    public static final String KEY_NAME_BASE = "user";
    private final RedisTemplate<String, User> userRedisTemplate;

    public Optional<User> get(long id) {
        return get(KEY_NAME_BASE + id);
    }

    public Optional<User> get(String key) {
        return Optional.ofNullable(userRedisTemplate.opsForValue().get(key));
    }

    public void set(long id, User user) {
        set(KEY_NAME_BASE + id, user);
    }

    public void set(String key, User user) {
        userRedisTemplate.opsForValue()
                         .set(key, user);
    }

    public void set(long id, User user, long seconds) {
        set(KEY_NAME_BASE + id, user, seconds);
    }

    public void set(String key, User user, long seconds) {
        userRedisTemplate.opsForValue()
                         .set(key, user, seconds, TimeUnit.SECONDS);
    }

    public void delete(long id) {
        delete(KEY_NAME_BASE + id);
    }

    public void delete(String key) {
        userRedisTemplate.delete(key);
    }
}
