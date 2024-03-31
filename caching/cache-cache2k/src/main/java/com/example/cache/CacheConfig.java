package com.example.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.cache.Cache2kBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public Cache2kBuilderCustomizer cache2kBuilderCustomizer() {
        return builder -> builder.entryCapacity(10)
                                 .expireAfterWrite(1, TimeUnit.MINUTES);
    }
}
