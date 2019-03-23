package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.support.HttpRequestInfo;

@RestController
public class ApiController {

    @GetMapping("/")
    public HttpRequestInfo index(HttpRequestInfo httpRequestInfo) {
        return httpRequestInfo;
    }
}
