package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/api/{code}")
    public ResponseEntity<String> mock(@PathVariable int code) {
        return new ResponseEntity<>("ok", HttpStatus.valueOf(code));
    }
}
