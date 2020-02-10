package com.example;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = Constants.CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Cacheable(key = "'user:' + #id")
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @CacheEvict(key = "'users'")
    public void evictCache() {
        log.info("@CacheEvict");
    }

    @CacheEvict(key = "'user:' + #id")
    public void evictCache(int id) {
        log.info("@CacheEvict id:{}", id);
    }

    @CachePut(key = "'user:' + #id")
    public User updateCache(int id) {
        log.info("@CachePut id:{}", id);
        return userRepository.findById(id).orElseThrow();
    }
}
