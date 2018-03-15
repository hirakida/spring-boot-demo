package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/internal")
    public String internal() {
        return "internal";
    }
}
