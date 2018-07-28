package com.example.core;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.config.SSMConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = SSMConfig.CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return repository.findAll();
    }

    @CacheEvict(key = "'users'")
    public void cacheEvict() {
        log.info("CacheEvict");
    }

    @Cacheable(key = "'user:' + #id")
    public User findById(int id) {
        log.info("findById id={}", id);
        return repository.findById(id)
                         .orElseThrow(() -> new RuntimeException("id: " + id));
    }

    @CacheEvict(key = "'user:' + #id")
    public void cacheEvict(int id) {
        log.info("CacheEvict id={}", id);
    }
}
