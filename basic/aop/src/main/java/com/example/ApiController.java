package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable long id) {
        return accountService.getAccount(id);
    }

    @GetMapping("/accounts/{id}/name")
    public String getName(@PathVariable long id) {
        return accountService.getName(id);
    }

    @PutMapping("/accounts/{id}")
    public Account putAccount(@PathVariable long id, @RequestParam String name) {
        return accountService.putAccount(id, name);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/accounts")
    public Account postAccount(@PathVariable String name) {
        return accountService.postAccount(name);
    }

    @GetMapping("/exception")
    public String exception() {
        throw new RuntimeException();
    }
}
