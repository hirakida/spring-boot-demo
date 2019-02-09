package com.example.core;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.config.CacheManagerConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = CacheManagerConfig.CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        log.info("@Cacheable findAll");
        return userRepository.findAll();
    }

    @CacheEvict(key = "'users'")
    public void cacheEvictAll() {
        log.info("@CacheEvict");
    }

    @Cacheable(key = "'user:' + #id")
    public User findOne(long id) {
        log.info("@Cacheable id:{}", id);
        return userRepository.findOne(id);
    }

    @CachePut(key = "'user:' + #id")
    public User cachePut(long id) {
        log.info("@CachePut id:{}", id);
        return userRepository.findOne(id);
    }

    @CacheEvict(key = "'user:' + #id")
    public void cacheEvict(long id) {
        log.info("@CacheEvict id:{}", id);
    }
}
