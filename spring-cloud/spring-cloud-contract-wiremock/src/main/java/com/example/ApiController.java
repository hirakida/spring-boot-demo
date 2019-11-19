package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final WeatherApiClient weatherApiClient;

    @GetMapping("/weather/{city}")
    public JsonNode getWeather(@PathVariable String city) {
        return weatherApiClient.getWeather(city);
    }
}
