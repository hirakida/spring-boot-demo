package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final BackEndApiClient backEndApiClient;

    @GetMapping("/datetime")
    public JsonNode getDateTIme() {
        return backEndApiClient.getDateTime();
    }
}
