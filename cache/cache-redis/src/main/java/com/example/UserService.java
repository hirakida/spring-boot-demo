package com.example;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.config.CachingConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = CachingConfig.CACHE_NAME)
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(key = "'user:' + #id")
    public User findById(long id) {
        log.info("@Cacheable id={}", id);
        return userRepository.findById(id);
    }

    @CacheEvict(key = "'user:' + #id")
    public void cacheEvict(long id) {
        log.info("@CacheEvict id={}", id);
    }
}
