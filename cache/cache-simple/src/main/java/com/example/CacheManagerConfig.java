package com.example;

import java.util.List;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheManagerConfig implements CacheManagerCustomizer<SimpleCacheManager> {
    public static final String CACHE_NAME = "cache1";

    @Override
    public void customize(SimpleCacheManager cacheManager) {
        ConcurrentMapCache cache = new ConcurrentMapCache(CACHE_NAME, false);
        cacheManager.setCaches(List.of(cache));
    }
}
