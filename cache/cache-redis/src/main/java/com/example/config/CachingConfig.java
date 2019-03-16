package com.example.config;

import java.time.Duration;
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

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CachingConfig extends CachingConfigurerSupport {
    public static final String CACHE_NAME = "default";
    private static final long EXPIRATION = 60;
    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration configuration =
                RedisCacheConfiguration.defaultCacheConfig()
                                       .entryTtl(Duration.ofSeconds(EXPIRATION))
                                       .disableCachingNullValues();
        Map<String, RedisCacheConfiguration> configurationMap = Map.of(CACHE_NAME, configuration);
        return RedisCacheManager.builder(redisConnectionFactory)
                                .withInitialCacheConfigurations(configurationMap)
                                .build();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandlerImpl();
    }
}
