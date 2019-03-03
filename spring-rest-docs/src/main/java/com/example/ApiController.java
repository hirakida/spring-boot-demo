package com.example;

import java.util.List;

import javax.validation.constraints.NotEmpty;

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
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account findById(@PathVariable int id) {
        return getById(id);
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody @Validated Request request) {
        Account account = new Account();
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        return accountRepository.save(account);
    }

    @PutMapping("/accounts/{id}")
    public Account update(@PathVariable int id, @RequestBody @Validated Request request) {
        Account account = getById(id);
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        accountRepository.deleteById(id);
    }

    private Account getById(int id) {
        return accountRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Data
    public static class Request {
        private @NotEmpty String name;
        private @NotEmpty String email;
    }
}
