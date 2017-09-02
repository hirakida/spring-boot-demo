package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = "default")
@Slf4j
public class AccountService {

    @Cacheable(key = "'account:' + #accountId")
    public Account cacheable(long accountId) {
        // cacheされている場合はメソッドが呼ばれないので、このlogは出ない
        log.info("@Cacheable accountId:{}", accountId);
        return Account.of(accountId);
    }

    @CachePut(key = "'account:' + #accountId")
    public Account cachePut(long accountId) {
        // 毎回呼ばれてcacheを更新する
        // cacheableの結果も変わる
        log.info("@CachePut");
        return Account.of(accountId);
    }

    // clear cache
    @CacheEvict(key = "'account:' + #accountId")
    public void cacheEvict(long accountId) {
        log.info("@CacheEvict accountId:{}", accountId);
    }

    // Listをcacheする
    @Cacheable(key = "'accounts'")
    public List<Account> cacheableAll() {
        log.info("@Cacheable findAll");
        return Stream.of(1L, 2L, 3L)
                     .map(Account::of)
                     .collect(Collectors.toList());
    }

    @CacheEvict(key = "'accounts'")
    public void cacheEvictAll() {
        log.info("@CacheEvict");
    }
}
