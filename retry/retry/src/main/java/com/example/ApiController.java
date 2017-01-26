package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ApiController {

    final ApiClient apiClient;

    @GetMapping("/api/{code}")
    public ResponseEntity<String> mock(@PathVariable int code) {
        return new ResponseEntity<>("ok", HttpStatus.valueOf(code));
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id) {
        log.info("controller start id={}", id);
        String result = apiClient.useRetryable(id);
        log.info("controller end id={} result={}", id, result);
        return result;
    }
}
