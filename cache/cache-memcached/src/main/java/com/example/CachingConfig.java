package com.example;

import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.ssm.Cache;
import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.spymemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.spring.SSMCache;
import com.google.code.ssm.spring.SSMCacheManager;

@EnableCaching
@Configuration
public class CachingConfig extends CachingConfigurerSupport {

    public static final String CACHE_NAME = "default_cache";
    private static final String ADDRESS = "127.0.0.1:11211";
    private static final int EXPIRATION = 60; // sec

    @Bean
    @Override
    public CacheManager cacheManager() {
        SSMCacheManager ssmCacheManager = new SSMCacheManager();
        SSMCache ssmCache = new SSMCache(defaultCache(), EXPIRATION, true);
        ssmCacheManager.setCaches(Collections.singletonList(ssmCache));
        return ssmCacheManager;
    }

    private static Cache defaultCache() {
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheName(CACHE_NAME);
        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cacheFactory.setAddressProvider(new DefaultAddressProvider(ADDRESS));
        CacheConfiguration config = new CacheConfiguration();
        config.setConsistentHashing(true);
        config.setUseBinaryProtocol(true);
        cacheFactory.setConfiguration(config);
        try {
            return cacheFactory.getObject();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
