package com.example;

import static com.example.WeatherApiClient.DEFAULT_CITY_CODE;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    private final WeatherApiClient weatherApiClient;

    public WeatherController(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    @GetMapping
    public Map<?, ?> getWeather(@RequestParam(defaultValue = DEFAULT_CITY_CODE) String city) {
        return weatherApiClient.getWeather(city);
    }
}
