package com.example;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = CachingConfig.CACHE_NAME)
@AllArgsConstructor
@Slf4j
public class AccountService {

    final AccountRepository repository;

    @Cacheable(key = "'account:all'")
    public List<Account> findAll() {
        return repository.findAll();
    }

    @CacheEvict(key = "'account:all'")
    public void clearCache() {
        log.info("AccountService::clearCache all");
    }

    @Cacheable(key = "'account:' + #id")
    public Account findOne(int id) {
        log.info("AccountService::findOne id={}", id);
        return repository.findOne(id);
    }

    @CacheEvict(key = "'account:' + #id")
    public void clearCache(int id) {
        log.info("AccountService::clearCache id={}", id);
    }
}
