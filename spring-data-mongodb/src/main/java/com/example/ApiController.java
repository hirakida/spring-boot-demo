package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public Page<Account> findAll(@PageableDefault Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @DeleteMapping("/accounts")
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @PostMapping("/accounts")
    public void create(@RequestBody @Validated Request request) {
        Account account = new Account();
        BeanUtils.copyProperties(request, account);
        accountRepository.save(account);
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable String id) {
        return accountRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/accounts/{id}")
    public void update(@PathVariable String id, @RequestBody @Validated Request request) {
        accountRepository.findById(id)
                         .ifPresent(account -> {
                             account.setName(request.getName());
                             accountRepository.save(account);
                         });
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable String id) {
        accountRepository.deleteById(id);
    }

    @Data
    public static class Request {
        private @NotNull String id;
        private @NotNull String name;
    }
}
