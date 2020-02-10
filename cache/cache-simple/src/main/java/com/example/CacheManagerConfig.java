package com.example;

import java.util.List;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheManagerConfig implements CacheManagerCustomizer<SimpleCacheManager> {

    @Override
    public void customize(SimpleCacheManager cacheManager) {
        List<ConcurrentMapCache> caches = List.of(new ConcurrentMapCache(Constants.CACHE_NAME, false));
        cacheManager.setCaches(caches);
    }
}
