package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final StringRepository stringRepository;

    @PutMapping("/{key}/{oldValue}/{newValue}")
    public Boolean set(@PathVariable String key,
                       @PathVariable String oldValue,
                       @PathVariable String newValue) {
        return stringRepository.checkAndSet(key, oldValue, newValue);
    }

    @GetMapping("/exists/{key}/{value}")
    public Boolean exists(@PathVariable String key, @PathVariable String value) {
        return stringRepository.exists(key, value);
    }
}
