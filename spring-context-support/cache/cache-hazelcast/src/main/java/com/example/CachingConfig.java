package com.example;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@EnableCaching
@Configuration
public class CachingConfig {
    static final String CACHE_NAME = "cache1";

    @Bean
    public Config hazelcastConfig() {
        MapConfig mapConfig = new MapConfig()
                .setName(CACHE_NAME)
                .setTimeToLiveSeconds(20);
        return new Config()
                .setInstanceName("instance1")
                .addMapConfig(mapConfig);
    }
}
