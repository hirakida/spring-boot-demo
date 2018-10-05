package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/api")
    public Response get(@RequestParam(defaultValue = "1") long id) {
        log.info("id: {}", id);
        validation(id);
        return new Response(id);
    }

    @PostMapping("/api")
    public Response post(@RequestBody Request request) {
        log.info("{}", request);
        validation(request.getId());
        return new Response(request.getId());
    }

    private static void validation(long id) {
        if (id > 10) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Data
    @NoArgsConstructor
    public static class Request {
        private long id;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private long id;
    }
}
