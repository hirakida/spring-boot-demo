package com.example;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        users = List.of(new User(1, "name1"),
                        new User(2, "name2"),
                        new User(3, "name3"),
                        new User(4, "name4"),
                        new User(5, "name5"));
    }

    @Cacheable(cacheNames = "cache1", key = "'users'")
    public List<User> findAll() {
        return users;
    }

    @CacheEvict(cacheNames = "cache1", key = "'users'")
    public void evictCache() {
    }
}
