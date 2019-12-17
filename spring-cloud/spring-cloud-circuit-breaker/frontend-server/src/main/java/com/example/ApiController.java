package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final BackendApiClient backEndApiClient;

    @GetMapping("/")
    public JsonNode getCount() {
        return backEndApiClient.getCount();
    }
}
