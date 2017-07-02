package com.example.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import com.example.domain.Account;
import com.example.domain.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class MyItemReader implements ItemReader<Account> {
    private static int index;
    private final AccountRepository accountRepository;

    @Override
    public Account read() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.size() <= index) {
            index = 0;
            return null;
        }
        log.info("read {}", accounts.get(index));
        return accounts.get(index++);
    }
}
