package com.example;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

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

    @PostMapping("/v3/hello")
    public JsonNode hello3(@RequestBody JsonNode body) {
        return body;
    }
}
