package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountController {

    final AccountRepository accountRepository;

    /**
     * pageとsizeを指定できる
     * 省略した場合はdefault値が設定される
     */
    @GetMapping("/api/accounts")
    public Page<Account> getAccounts(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
}
