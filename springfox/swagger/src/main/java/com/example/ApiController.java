package com.example;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api")
public class ApiController {

    AtomicLong id = new AtomicLong(1L);

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return IntStream.rangeClosed(1, 5)
                        .mapToObj(Account::of)
                        .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable long id) {
        return Account.of(id);
    }

    @PostMapping("/accounts")
    public Account addAccount(@RequestParam String name) {
        return new Account(id.getAndIncrement(), name);
    }

    @PutMapping("/accounts/{id}")
    public Account updateAccount(@PathVariable long id,
                                 @RequestParam String name) {
        return new Account(id, name);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable long id) {
    }

    @Data
    @AllArgsConstructor
    public static class Account {
        private long id;
        private String name;

        public static Account of(long id) {
            return new Account(id, "name" + id);
        }
    }
}
