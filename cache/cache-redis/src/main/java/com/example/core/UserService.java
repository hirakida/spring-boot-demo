package com.example.core;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.config.CachingConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = CachingConfig.CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Cacheable(key = "'user:' + #id")
    public User cacheable(long id) {
        log.info("@Cacheable id={}", id);
        return userRepository.findOne(id);
    }

    @CacheEvict(key = "'user:' + #id")
    public void cacheEvict(long id) {
        log.info("@CacheEvict id={}", id);
    }
}
