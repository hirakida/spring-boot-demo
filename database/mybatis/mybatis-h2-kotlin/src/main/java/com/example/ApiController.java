package com.example;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ApiController {

    final AccountMapper accountMapper;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account findOne(@PathVariable long id) {
        return accountMapper.findOne(id);
    }

    @PostMapping("/accounts")
    public void create(@RequestBody Account account) {
        accountMapper.insert(account);
    }

    @PutMapping("/accounts/{id}")
    public void update(@PathVariable long id, @RequestBody Account account) {
        accountMapper.update(account);
    }

    @DeleteMapping("/accounts/{id}")
    public void delete(@PathVariable long id) {
        accountMapper.delete(id);
    }
}
