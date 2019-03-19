package com.example.service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Account;
import com.example.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;

    public List<Account> findAll(Pageable pageable) {
        return accountMapper.findAll(toRowBounds(pageable));
    }

    public List<Account> findAll() {
        Cursor<Account> cursor = accountMapper.findAllWithCursor();
        try (cursor) {
            return StreamUtils.createStreamFromIterator(cursor.iterator())
                              .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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
