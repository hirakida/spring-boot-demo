package com.example;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class ApiController {

    @GetMapping
    public Response index() {
        return new Response("Hello, Google App Engine!", LocalDateTime.now());
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private String message;
        private LocalDateTime localDateTime;
    }
}
