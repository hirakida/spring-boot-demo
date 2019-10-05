package com.example.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ApiController {

    @GetMapping("/api/{statusCode}")
    public ApiResponse status(@PathVariable int statusCode) {
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        if (httpStatus.isError()) {
            throw new ResponseStatusException(httpStatus);
        }
        return new ApiResponse(httpStatus);
    }
}
