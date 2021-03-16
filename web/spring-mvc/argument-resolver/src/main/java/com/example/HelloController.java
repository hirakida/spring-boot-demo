package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.RequestInfo;

@RestController
public class HelloController {

    @GetMapping("/")
    public RequestInfo hello(RequestInfo requestInfo) {
        return requestInfo;
    }
}
