package com.example.config;

import static com.example.config.CachingConfig.CACHE_NAME;

import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class CachingMetricsConfig {
    private final CacheManager cacheManager;
    private final CacheMetricsRegistrar cacheMetricsRegistrar;

    public CachingMetricsConfig(CacheManager cacheManager,
                                CacheMetricsRegistrar cacheMetricsRegistrar) {
        this.cacheManager = cacheManager;
        this.cacheMetricsRegistrar = cacheMetricsRegistrar;
    }

    @PostConstruct
    public void init() {
        Cache cache = cacheManager.getCache(CACHE_NAME);
        cacheMetricsRegistrar.bindCacheToRegistry(cache);
    }
}
