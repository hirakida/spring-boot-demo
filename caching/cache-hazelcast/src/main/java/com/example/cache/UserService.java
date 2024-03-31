package com.example.cache;

import static com.example.cache.config.CachingConfig.CACHE_NAME;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = CACHE_NAME)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @CacheEvict(key = "'users'")
    public void evictCache() {
    }
}
