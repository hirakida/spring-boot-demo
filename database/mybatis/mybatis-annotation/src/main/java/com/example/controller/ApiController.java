package com.example.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.mapper.AccountMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final AccountMapper accountMapper;

    @GetMapping("/accounts")
    public List<Account> findAll(@PageableDefault Pageable pageable) {
        return accountMapper.findAll(toRowBounds(pageable));
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable long id) {
        return accountMapper.findOne(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated Request request) {
        Account account = new Account();
        account.setName(request.getName());
        accountMapper.insert(account);
    }

    @PutMapping("/accounts/{id}")
    public void update(@PathVariable long id, @RequestBody @Validated Request request) {
        Account account = accountMapper.findOne(id);
        account.setName(request.getName());
        accountMapper.update(account);
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        Account account = accountMapper.findOne(id);
        accountMapper.delete(account.getId());
    }

    private static RowBounds toRowBounds(Pageable pageable) {
        return new RowBounds((int) pageable.getOffset(), pageable.getPageSize());
    }

    @Data
    public static class Request {
        private @NotEmpty String name;
    }
}
