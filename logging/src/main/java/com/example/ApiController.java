package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        log.info("hello!");
        return "hello!";
    }
}
