package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/text")
    public String getTest(@RequestParam(defaultValue = "1") long id) {
        if (id > 10) {
            throw new ResourceAccessException("not found " + id);
        }
        return "ok";
    }

    @GetMapping("/entity")
    public ApiResponse getEntity(@RequestParam(defaultValue = "1") long id) {
        if (id > 10) {
            throw new ResourceAccessException("not found " + id);
        }
        return new ApiResponse(id);
    }

    @Data
    @AllArgsConstructor
    public static class ApiResponse {
        private long id;
    }
}
