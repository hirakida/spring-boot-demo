package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class ApiController {
    private final ApiClient apiClient;

    public ApiController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @GetMapping("/{city}")
    public JsonNode getWeather(@PathVariable String city) {
        return apiClient.getWeather(city);
    }
}
