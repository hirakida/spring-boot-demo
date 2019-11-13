package com.example;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class ApiController {

    @GetMapping("/datetime")
    public Response getDateTime() {
        return new Response(LocalDateTime.now());
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private LocalDateTime dateTime;
    }
}
