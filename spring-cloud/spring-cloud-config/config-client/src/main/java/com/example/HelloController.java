package com.example;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final AppProperties properties;

    @GetMapping("/")
    public Map<String, Object> hello() {
        return Map.of("message1", properties.getMessage1(),
                      "message2", properties.getMessage2(),
                      "number1", properties.getNumber1(),
                      "number2", properties.getNumber2());
    }
}
