package com.example.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class CachingConfig {
    public static final String CACHE_NAME = "cache1";

    @Bean
    public Config hazelcastConfig() {
        MapConfig mapConfig = new MapConfig(CACHE_NAME);
        mapConfig.setTimeToLiveSeconds(60);
        Config config = new Config();
        config.addMapConfig(mapConfig);
        return config;
    }
}
