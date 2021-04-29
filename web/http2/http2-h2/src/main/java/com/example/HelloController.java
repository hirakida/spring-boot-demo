package com.example;

import javax.servlet.http.PushBuilder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index(PushBuilder pushBuilder) {
        if (pushBuilder != null) {
            pushBuilder.path("main.css").push();
        }
        return "index";
    }
}
