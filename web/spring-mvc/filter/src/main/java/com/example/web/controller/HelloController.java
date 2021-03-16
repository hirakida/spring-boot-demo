package com.example.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/v1/hello")
    public String hello1() {
        return "Hello1";
    }

    @GetMapping("/v2/hello")
    public String hello2() {
        return "Hello2";
    }
}
