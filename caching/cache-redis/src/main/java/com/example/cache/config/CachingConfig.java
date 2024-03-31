package com.example.cache.config;

import java.time.Duration;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import lombok.RequiredArgsConstructor;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CachingConfig implements CachingConfigurer {
    public static final String CACHE_NAME = "cache1";
    private final RedisConnectionFactory connectionFactory;

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                                                                       .entryTtl(Duration.ofSeconds(60))
                                                                       .disableCachingNullValues();
        return RedisCacheManager.builder(connectionFactory)
                                .withInitialCacheConfigurations(Map.of(CACHE_NAME, configuration))
                                .enableStatistics()
                                .build();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandlerImpl();
    }
}
