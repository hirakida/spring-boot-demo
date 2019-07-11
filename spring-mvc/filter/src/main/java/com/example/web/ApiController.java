package com.example.web;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class ApiController {

    @GetMapping("/v1")
    public Response v1() {
        return new Response(LocalDateTime.now());
    }

    @GetMapping("/v2")
    public Response v2() {
        return new Response(LocalDateTime.now());
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private LocalDateTime dateTime;
    }
}
