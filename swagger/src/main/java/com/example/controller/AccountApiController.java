package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Account;
import com.example.domain.AccountRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(description = "api controller for account")
@AllArgsConstructor
public class AccountApiController {

    final AccountRepository accountRepository;

    @GetMapping("/accounts")
    @ApiOperation(value = "get accounts")
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    @ApiOperation(value = "get account")
    public Account findOne(@ApiParam @PathVariable int id) {
        return accountRepository.findOne(id);
    }

    @PostMapping("/accounts")
    @ApiOperation(value = "create account")
    public Account create(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("/accounts/{id}")
    @ApiOperation(value = "update account")
    public Account update(@PathVariable int id,
                          @RequestBody Account account) {
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts/{id}")
    @ApiOperation(value = "delete account")
    public void delete(@PathVariable int id) {
        accountRepository.delete(id);
    }

    @ApiIgnore
    public void dummy() {
        // dummy
    }
}
