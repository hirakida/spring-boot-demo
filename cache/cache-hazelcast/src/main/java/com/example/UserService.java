package com.example;

import static com.example.CachingConfig.CACHE_NAME;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @CacheEvict(key = "'users'")
    public void deleteCache() {
        log.info("@CacheEvict");
    }
}
