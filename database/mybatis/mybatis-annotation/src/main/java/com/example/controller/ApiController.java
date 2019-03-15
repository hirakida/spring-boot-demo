package com.example.controller;

import java.util.List;

import javax.validation.constraints.NotEmpty;

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
import com.example.service.AccountService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> findAll(@PageableDefault Pageable pageable) {
        return accountService.findAll(pageable);
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable long id) {
        return accountService.findById(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Validated Request request) {
        accountService.create(request.getName());
    }

    @PutMapping("/accounts/{id}")
    public void update(@PathVariable long id, @RequestBody @Validated Request request) {
        accountService.update(id, request.getName());
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        accountService.delete(id);
    }

    @Data
    public static class Request {
        private @NotEmpty String name;
    }
}
