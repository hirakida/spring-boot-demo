package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.AccountRedisClient;
import com.example.client.StringRedisClient;
import com.example.entity.Account;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    final StringRedisClient stringRedisClient;
    final AccountRedisClient accountRedisClient;

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return stringRedisClient.get(key)
                                .orElseThrow(DataNotFoundException::new);
    }

    @PutMapping("/{key}")
    public Boolean expire(@PathVariable String key, @RequestParam long seconds) {
        return stringRedisClient.expire(key, seconds);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable String key) {
        stringRedisClient.delete(key);
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable long id) {
        return accountRedisClient.get(id)
                                 .orElseThrow(DataNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @NoArgsConstructor
    @SuppressWarnings("serial")
    public static class DataNotFoundException extends RuntimeException {
    }
}
