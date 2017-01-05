package com.example;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@CacheConfig(cacheNames = CachingConfig.CACHE_NAME)
public class AccountService {

    @Cacheable(key = "'account:' + #accountId")
    public Account cacheable(long accountId) {
        log.info("AccountService::cacheable");
        return Account.builder()
                      .id(accountId)
                      .name("name" + accountId)
                      .build();
    }

    @CacheEvict(key = "'account:' + #accountId")
    public void cacheEvict(long accountId) {
        log.info("AccountService::cacheEvict");
    }
}
