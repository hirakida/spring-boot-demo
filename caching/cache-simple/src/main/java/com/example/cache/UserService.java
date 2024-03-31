package com.example.cache;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(cacheNames = "cache1", key = "'users'")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @CacheEvict(cacheNames = "cache1", key = "'users'")
    public void evictCache() {
    }
}
