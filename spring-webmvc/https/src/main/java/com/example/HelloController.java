package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello, HTTPS!";
    }

    @GetMapping("/json")
    public HelloResponse json() {
        return new HelloResponse("Hello!");
    }
}
