package com.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.StringRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final StringRepository stringRepository;

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return stringRepository.get(key)
                               .orElseThrow(DataNotFoundException::new);
    }

    @PutMapping("/{key}")
    public Boolean expire(@PathVariable String key, @RequestParam long seconds) {
        return stringRepository.expire(key, seconds);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable String key) {
        stringRepository.delete(key);
    }

    @GetMapping("/script/{key}/{value}")
    public Boolean script(@PathVariable String key, @PathVariable String value) {
        return stringRepository.exists(key, value);
    }
}
