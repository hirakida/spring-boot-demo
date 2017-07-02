package com.example.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.domain.Account;
import com.example.domain.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class MyItemWriter implements ItemWriter<Account> {

    private final AccountRepository accountRepository;

    @Override
    public void write(List<? extends Account> accounts) throws Exception {
        log.info("write {}", accounts);
        accountRepository.save(accounts);
    }
}
