package com.example.config;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CachingConfig extends CachingConfigurerSupport {

    public static final String CACHE_NAME = "default";
    private static final long EXPIRATION = 60;
    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    @Override
    public CacheManager cacheManager() {
        Map<String, RedisCacheConfiguration> config =
                Collections.singletonMap(CACHE_NAME, redisCacheConfiguration(EXPIRATION));
        return RedisCacheManager.builder(redisConnectionFactory)
                                .withInitialCacheConfigurations(config)
                                .build();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandlerImpl();
    }

    private static RedisCacheConfiguration redisCacheConfiguration(long expiration) {
        return RedisCacheConfiguration.defaultCacheConfig()
                                      .entryTtl(Duration.ofSeconds(expiration))
                                      .disableCachingNullValues();
    }
}
