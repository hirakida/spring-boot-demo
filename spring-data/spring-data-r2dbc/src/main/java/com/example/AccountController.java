package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public Flux<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> findById(@PathVariable int id) {
        return accountService.findById(id);
    }

    @PostMapping
    public Mono<Account> create(@RequestBody Request request) {
        Account account = new Account();
        account.setName(request.getName());
        return accountService.create(account);
    }

    @PutMapping("/{id}")
    public Mono<Account> update(@PathVariable int id, @RequestBody Request request) {
        Account account = new Account();
        account.setId(id);
        account.setName(request.getName());
        return accountService.update(account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable int id) {
        return accountService.deleteById(id);
    }

    @Data
    public static class Request {
        private String name;
    }
}
