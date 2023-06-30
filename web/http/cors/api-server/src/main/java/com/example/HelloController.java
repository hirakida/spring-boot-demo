package com.example;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/hello")
    public Response hello() {
        return new Response("Hello!");
    }

    public record Response(String content) {}
}
