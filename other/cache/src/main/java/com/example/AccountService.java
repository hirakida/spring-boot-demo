package com.example;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    public Account findById(long accountId) {
        // cacheされている場合はこのlogは出ない
        log.info("AccountService::findById");
        return accountRepository.findById(accountId);
    }

    // Listをcacheする
    @Cacheable(value = "value1", key = "'accounts'")
    public List<Account> findAll() {
        log.info("AccountService::findAll");
        return Arrays.asList(1L, 2L, 3L).stream()
                     .map(accountRepository::findById)
                     .collect(toList());
    }

    // クラス内から@Cacheableのメソッドを呼出してもキャッシュされない
    public List<Account> findById2() {
        log.info("AccountService::findById2");
        return Arrays.asList(1L, 2L, 3L).stream()
                     .map(this::findById)
                     .collect(toList());
    }

    // clear cache
    @CacheEvict(value = "value1", key = "'account:' + #accountId")
    public void delete(long accountId) {
        log.info("AccountService::delete");
    }

    @CacheEvict(value = "value1", key = "'accounts'")
    public void deleteAll() {
        log.info("AccountService::deleteAll");
    }
}
