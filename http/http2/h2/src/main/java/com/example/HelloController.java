package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.PushBuilder;

@Controller
public class HelloController {
    @GetMapping("/")
    public String index(PushBuilder pushBuilder) {
        if (pushBuilder != null) {
            pushBuilder.path("/main.css").push();
            pushBuilder.path("/main.js").push();
        }
        return "index";
    }
}
