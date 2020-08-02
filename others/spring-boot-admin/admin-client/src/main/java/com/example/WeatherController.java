package com.example;

import static com.example.WeatherApiClient.DEFAULT_CITY_CODE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Weather;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherApiClient weatherApiClient;

    @GetMapping
    public Weather getWeather(@RequestParam(defaultValue = DEFAULT_CITY_CODE) String city) {
        return weatherApiClient.getWeather(city);
    }
}
