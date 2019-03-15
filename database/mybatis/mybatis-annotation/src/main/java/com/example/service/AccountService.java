package com.example.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;

    public List<Account> findAll(Pageable pageable) {
        return accountMapper.findAll(toRowBounds(pageable));
    }

    public Account findById(long id) {
        return accountMapper.findById(id).orElseThrow();
    }

    public void create(String name) {
        Account account = new Account();
        account.setName(name);
        accountMapper.insert(account);
    }

    public void update(long id, String name) {
        Account account = accountMapper.findById(id).orElseThrow();
        account.setName(name);
        accountMapper.update(account);
    }

    public void delete(long id) {
        accountMapper.delete(id);
    }

    private static RowBounds toRowBounds(Pageable pageable) {
        return new RowBounds((int) pageable.getOffset(), pageable.getPageSize());
    }
}
