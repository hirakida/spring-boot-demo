package com.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Value;

@RestController
public class HelloController {

    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/hello")
    public Response hello() {
        return new Response("Hello!");
    }

    @Value
    public static class Response {
        String content;
    }
}
