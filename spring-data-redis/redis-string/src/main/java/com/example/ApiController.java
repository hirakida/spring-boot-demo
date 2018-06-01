package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final StringRepository stringRepository;

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return stringRepository.get(key)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{key}")
    public Boolean expire(@PathVariable String key, @RequestParam long seconds) {
        return stringRepository.expire(key, seconds);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable String key) {
        stringRepository.delete(key);
    }

    @GetMapping("/exists/{key}/{value}")
    public Boolean exists(@PathVariable String key, @PathVariable String value) {
        return stringRepository.exists(key, value);
    }
}
