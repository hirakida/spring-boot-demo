package com.example.config;

import java.util.Collections;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableCaching
@Configuration
@AllArgsConstructor
@Slf4j
public class CachingConfig extends CachingConfigurerSupport {

    public static final String CACHE_NAME = "default_cache";
    private static final int EXPIRATION = 60; // sec

    final RedisTemplate<Object, Object> redisTemplate;

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(EXPIRATION);
        cacheManager.setUsePrefix(true);
        cacheManager.setCacheNames(Collections.singletonList(CACHE_NAME));
        return cacheManager;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandlerImpl();
    }

    public static class CacheErrorHandlerImpl implements CacheErrorHandler {
        @Override
        public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
            log.error("{} {} {}", exception, cache, key);
        }

        @Override
        public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
            log.error("{} {} {} {}", exception, cache, key, value);
        }

        @Override
        public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
            log.error("{} {} {}", exception, cache, key);throw exception;
        }

        @Override
        public void handleCacheClearError(RuntimeException exception, Cache cache) {
            log.error("{} {}", exception, cache);
        }
    }
}
