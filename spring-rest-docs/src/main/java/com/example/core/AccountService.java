package com.example.core;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ApiController.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findOne(int id) {
        return Optional.ofNullable(accountRepository.findOne(id))
                       .orElseThrow(DataNotFoundException::new);
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Account update(Account account) {
        findOne(account.getId());
        return accountRepository.save(account);
    }

    public void delete(int id) {
        findOne(id);
        accountRepository.delete(id);
    }
}
