package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/datetime")
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    @GetMapping("/date")
    public LocalDate getDate() {
        return LocalDate.now();
    }
}
