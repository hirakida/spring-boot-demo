package com.example;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // cacheする
    @Cacheable(value = "value1", key = "'account:' + #accountId")
    public Account cacheable(long accountId) {
        // cacheされている場合はメソッドが呼ばれないので、このlogは出ない
        log.info("AccountService::cacheable");
        return accountRepository.findById(accountId);
    }

    @CachePut(value = "value1", key = "'account:' + #accountId")
    public Account cachePut(long accountId) {
        // 毎回呼ばれてcacheを更新する
        // cacheableの結果も変わる
        log.info("AccountService::cachePut");
        return accountRepository.update(accountId);
    }

    // Listをcacheする
    @Cacheable(value = "value1", key = "'accounts'")
    public List<Account> cacheableAll() {
        log.info("AccountService::findAll");
        return Arrays.asList(1L, 2L, 3L).stream()
                     .map(accountRepository::findById)
                     .collect(toList());
    }

    // クラス内から@Cacheableのメソッドを呼出してもキャッシュされない
    public List<Account> findById() {
        log.info("AccountService::findById");
        return Arrays.asList(1L, 2L, 3L).stream()
                     .map(this::cacheable)
                     .collect(toList());
    }

    // clear cache
    @CacheEvict(value = "value1", key = "'account:' + #accountId")
    public void cacheEvict(long accountId) {
        log.info("AccountService::cacheEvict");
    }

    @CacheEvict(value = "value1", key = "'accounts'")
    public void cacheEvictAll() {
        log.info("AccountService::cacheEvictAll");
    }
}
