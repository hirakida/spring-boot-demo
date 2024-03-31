package com.example.cache;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "cache1")
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Cacheable(key = "'user:' + #id")
    public User findById(int id) {
        return userRepository.findAll()
                             .stream()
                             .filter(user -> user.id() == id)
                             .findFirst()
                             .orElseThrow();
    }

    @CacheEvict(key = "'users'")
    public void evictCache() {
    }

    @CacheEvict(key = "'user:' + #id")
    public void evictCache(int id) {
    }
}
