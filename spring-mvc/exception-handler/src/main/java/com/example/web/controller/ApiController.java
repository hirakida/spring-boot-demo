package com.example.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.web.ApiResponse;

@RestController
public class ApiController {

    @GetMapping("/api/{statusCode}")
    public ApiResponse status(@PathVariable int statusCode) {
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        if (!httpStatus.is2xxSuccessful()) {
            throw new ResponseStatusException(httpStatus);
        }
        return new ApiResponse(httpStatus);
    }
}
