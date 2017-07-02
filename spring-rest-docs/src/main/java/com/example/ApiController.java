package com.example;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    final AccountRepository repository;

    @GetMapping("/accounts")
    public List<Account> getAll() {
        return repository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account get(@PathVariable int id) {
        Account account = repository.findOne(id);
        if (account == null) {
            throw new DataNotFoundException();
        }
        return account;
    }

    @PutMapping("/accounts/{id}")
    public Account put(@PathVariable int id,
                       @RequestBody @Validated Account account) {
        if (repository.findOne(id) == null) {
            throw new DataNotFoundException();
        }
        return repository.save(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable int id) {
        if (repository.findOne(id) == null) {
            throw new DataNotFoundException();
        }
        repository.delete(id);
    }

    @PostMapping("/accounts")
    public Account post(@RequestBody @Validated Account account) {
        return repository.save(account);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @SuppressWarnings("serial")
    public static class DataNotFoundException extends RuntimeException {
    }
}
