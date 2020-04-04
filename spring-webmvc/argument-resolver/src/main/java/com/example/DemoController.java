package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.RequestInfo;

@RestController
public class DemoController {

    @GetMapping("/")
    public RequestInfo demo(RequestInfo requestInfo) {
        return requestInfo;
    }
}
