package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/api")
    public Response api(@RequestParam(defaultValue = "1") long id) {
        log.info("id: {}", id);
        if (id > 10) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new Response(id);
    }

    @GetMapping("/exclude")
    public Response exclude(@RequestParam(defaultValue = "1") long id) {
        return new Response(id);
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private long id;
    }
}
