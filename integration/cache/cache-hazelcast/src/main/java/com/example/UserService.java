package com.example;

import static com.example.HazelcastConfig.CACHE_NAME;

import java.util.List;

import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = CACHE_NAME)
@Slf4j
public class UserService {
    List<User> users;

    public UserService(CacheManager cacheManager, CacheMetricsRegistrar cacheMetricsRegistrar) {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        cacheMetricsRegistrar.bindCacheToRegistry(cache);

        users = List.of(new User(1, "name1"),
                        new User(2, "name2"),
                        new User(3, "name3"),
                        new User(4, "name4"),
                        new User(5, "name5"));
    }

    @Cacheable(key = "'users'")
    public List<User> findAll() {
        return users;
    }

    @CacheEvict(key = "'users'")
    public void evictCache() {
        log.info("@CacheEvict");
    }
}
