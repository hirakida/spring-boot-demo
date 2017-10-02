package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ApiDataNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @GetMapping("/text")
    public String text(@RequestParam(defaultValue = "1") long id) {
        log.info("id: {}", id);
        validation(id);
        return "ok";
    }

    @GetMapping("/entity")
    public ApiResponse entity(@RequestParam(defaultValue = "1") long id) {
        log.info("id: {}", id);
        validation(id);
        return new ApiResponse(id);
    }

    private static void validation(long id) {
        if (id > 10) {
            throw new ApiDataNotFoundException("id: " + id);
        }
    }

    @Data
    @AllArgsConstructor
    public static class ApiResponse {
        private long id;
    }
}
