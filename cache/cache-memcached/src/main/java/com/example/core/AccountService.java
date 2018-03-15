package com.example.core;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.config.SSMConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = SSMConfig.CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository repository;

    @Cacheable(key = "'accounts'")
    public List<Account> findAll() {
        return repository.findAll();
    }

    @CacheEvict(key = "'accounts'")
    public void cacheEvict() {
        log.info("CacheEvict");
    }

    @Cacheable(key = "'account:' + #id")
    public Account findOne(int id) {
        log.info("findOne id={}", id);
        return repository.findOne(id);
    }

    @CacheEvict(key = "'account:' + #id")
    public void cacheEvict(int id) {
        log.info("CacheEvict id={}", id);
    }
}
