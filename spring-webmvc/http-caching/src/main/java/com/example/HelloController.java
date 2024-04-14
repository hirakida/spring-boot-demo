package com.example;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public Response hello() {
        return new Response("Hello! " + LocalDateTime.now());
    }

    public record Response(String message) {}
}
