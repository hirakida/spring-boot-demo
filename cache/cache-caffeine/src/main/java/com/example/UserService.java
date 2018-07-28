package com.example;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = "default")
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Cacheable(key = "'user:' + #id")
    public User findById(long id) {
        log.info("@Cacheable id:{}", id);
        return userRepository.findById(id);
    }

    @CacheEvict(key = "'user:' + #id")
    public void cacheEvict(long id) {
        log.info("@CacheEvict id:{}", id);
    }
}
