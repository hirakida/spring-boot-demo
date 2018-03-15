package com.example.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.seasar.doma.boot.Pageables;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.core.Account;
import com.example.core.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    /**
     * http://localhost:8080/accounts?page=0&size=2
     */
    @GetMapping("/accounts")
    public List<Account> findAll(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(Pageables.toSelectOptions(pageable));
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable long id) {
        return accountRepository.findOne(id);
    }

    @PostMapping("/accounts")
    public int create(@RequestBody @Validated AccountRequest request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setAge(request.getAge());
        return accountRepository.insert(account);
    }

    @PutMapping("/accounts/{id}")
    public int update(@PathVariable long id,
                      @RequestBody @Validated AccountRequest request) {
        Account account = accountRepository.findOne(id);
        account.setName(request.getName());
        account.setAge(request.getAge());
        return accountRepository.update(account);
    }

    @DeleteMapping("/accounts/{id}")
    public int update(@PathVariable long id) {
        Account account = accountRepository.findOne(id);
        return accountRepository.delete(account);
    }

    @Data
    @AllArgsConstructor
    public static class AccountRequest {
        @NotNull
        @Length(max = 30)
        private String name;
        @NotNull
        private Integer age;
    }
}
