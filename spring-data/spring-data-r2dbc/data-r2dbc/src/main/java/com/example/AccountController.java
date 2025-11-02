package com.example;

import java.time.LocalDateTime;

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
    private final AccountRepository repository;

    @GetMapping
    public Flux<Account> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Account> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public Mono<Account> create(@RequestBody Request request) {
        final Account account = new Account();
        account.setName(request.getName());
        account.setCreatedAt(LocalDateTime.now());
        return repository.save(account);
    }

    @PutMapping("/{id}")
    public Mono<Account> update(@PathVariable int id, @RequestBody Request request) {
        return repository.findById(id)
                         .flatMap(account -> {
                             account.setName(request.getName());
                             return repository.save(account);
                         });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable int id) {
        return repository.deleteById(id);
    }

    @Data
    public static class Request {
        private String name;
    }
}
