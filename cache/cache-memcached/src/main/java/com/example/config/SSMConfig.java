package com.example.config;

import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.AbstractSSMConfiguration;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.spymemcached.MemcacheClientFactoryImpl;
import com.google.code.ssm.spring.ExtendedSSMCacheManager;
import com.google.code.ssm.spring.SSMCache;

@EnableCaching
@Configuration
public class SSMConfig extends AbstractSSMConfiguration {
    public static final String CACHE_NAME = "default";
    private static final String ADDRESS = "127.0.0.1:11211";
    private static final int EXPIRATION = 60; // sec

    @Bean
    @Override
    public CacheFactory defaultMemcachedClient() {
        CacheConfiguration cacheConfig = new CacheConfiguration();
        cacheConfig.setConsistentHashing(true);
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheName(CACHE_NAME);
        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());
        cacheFactory.setAddressProvider(new DefaultAddressProvider(ADDRESS));
        cacheFactory.setConfiguration(cacheConfig);
        return cacheFactory;
    }

    @Bean
    public CacheManager cacheManager() throws Exception {
        ExtendedSSMCacheManager ssmCacheManager = new ExtendedSSMCacheManager();
        SSMCache ssmCache = new SSMCache(defaultMemcachedClient().getObject(), EXPIRATION, true);
        ssmCacheManager.setCaches(Collections.singletonList(ssmCache));
        return ssmCacheManager;
    }
}
