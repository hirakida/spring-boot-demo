package com.example;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = "cache1")
@Slf4j
public class UserService {
    List<User> users;

    public UserService() {
        users = List.of(new User(1, "name1"),
                        new User(2, "name2"),
                        new User(3, "name3"),
                        new User(4, "name4"),
                        new User(5, "name5"));
    }

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return users;
    }

    @CacheEvict(key = "'users'")
    public void evictCache() {
        log.info("@CacheEvict");
    }
}
