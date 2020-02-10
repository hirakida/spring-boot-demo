package com.example;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@EnableCaching
@Configuration
public class CachingConfig {
    @Bean
    public Config hazelcastConfig() {
        MapConfig mapConfig = new MapConfig(Constants.CACHE_NAME);
        mapConfig.setTimeToLiveSeconds(20);

        Config config = new Config("instance1");
        config.addMapConfig(mapConfig);
        return config;
    }
}
