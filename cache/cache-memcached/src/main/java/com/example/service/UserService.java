package com.example.service;

import static com.example.config.SSMConfig.CACHE_NAME;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.core.UserRepository;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return repository.findAll();
    }

    @Cacheable(key = "'user:' + #id")
    public User findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @CacheEvict(key = "'users'")
    public void deleteCache() {
        log.info("CacheEvict");
    }

    @CacheEvict(key = "'user:' + #id")
    public void deleteCache(int id) {
        log.info("CacheEvict id={}", id);
    }
}
