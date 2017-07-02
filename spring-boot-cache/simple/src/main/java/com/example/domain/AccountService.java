package com.example.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = "default")
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    // cacheする
    @Cacheable(key = "'account:' + #accountId")
    public Account cacheable(long accountId) {
        // cacheされている場合はメソッドが呼ばれないので、このlogは出ない
        log.info("AccountService::cacheable");
        return accountRepository.findById(accountId);
    }

    @CachePut(key = "'account:' + #accountId")
    public Account cachePut(long accountId) {
        // 毎回呼ばれてcacheを更新する
        // cacheableの結果も変わる
        log.info("AccountService::cachePut");
        return accountRepository.update(accountId);
    }

    // Listをcacheする
    @Cacheable(key = "'accounts'")
    public List<Account> cacheableAll() {
        log.info("AccountService::findAll");
        return Stream.of(1L, 2L, 3L)
                     .map(accountRepository::findById)
                     .collect(toList());
    }

    // clear cache
    @CacheEvict(key = "'account:' + #accountId")
    public void cacheEvict(long accountId) {
        log.info("AccountService::cacheEvict");
    }

    @CacheEvict(key = "'accounts'")
    public void cacheEvictAll() {
        log.info("AccountService::cacheEvictAll");
    }
}
