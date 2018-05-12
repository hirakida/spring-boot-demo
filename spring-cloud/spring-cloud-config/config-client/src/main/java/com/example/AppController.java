package com.example;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final AppProperties appProperties;

    @GetMapping("/")
    public Response index() {
        Response response = new Response();
        BeanUtils.copyProperties(appProperties, response);
        return response;
    }

    @Data
    public static class Response {
        private String message1;
        private String message2;
        private Integer number1;
        private Integer number2;
    }
}