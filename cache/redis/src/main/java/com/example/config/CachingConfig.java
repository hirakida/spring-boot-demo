package com.example.config;

import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.AllArgsConstructor;

@EnableCaching
@Configuration
@AllArgsConstructor
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
}
