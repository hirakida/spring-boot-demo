package com.example.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/")
    public RequestInfo index(RequestInfo requestInfo) {
        return requestInfo;
    }
}
