package com.example;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.config.CachingConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@CacheConfig(cacheNames = CachingConfig.CACHE_NAME)
public class AccountService {

    @Cacheable(key = "'account:' + #id")
    public Account cacheable(long id) {
        log.info("@Cacheable");
        return Account.of(id);
    }

    @CacheEvict(key = "'account:' + #id")
    public void cacheEvict(long id) {
        log.info("@CacheEvict");
    }
}
