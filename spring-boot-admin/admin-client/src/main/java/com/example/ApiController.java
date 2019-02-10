package com.example;

import static com.example.WeatherApiClient.DEFAULT_CITY_CODE;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final WeatherApiClient weatherApiClient;

    @GetMapping
    public Map<?, ?> getWeather(@RequestParam(defaultValue = DEFAULT_CITY_CODE) String cityCode) {
        return weatherApiClient.getWeather(cityCode);
    }
}
