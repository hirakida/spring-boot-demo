package com.hirakida.spring.boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final HelloBean helloBean;

    @GetMapping("/")
    public String getMessage() {
        return helloBean.getMessage();
    }
}
