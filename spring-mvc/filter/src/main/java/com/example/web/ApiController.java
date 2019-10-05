package com.example.web;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/v1/date")
    public Map<String, LocalDate> dateV1() {
        return Map.of("date", LocalDate.now());
    }

    @GetMapping("/v2/date")
    public Map<String, LocalDate> dateV2() {
        return Map.of("date", LocalDate.now());
    }
}
