package com.example;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.WeatherClient;
import com.example.client.model.Weather;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final WeatherClient weatherClient;

    @GetMapping("/now")
    @Timed("now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @GetMapping("/weather")
    @Timed("weather")
    public Weather weather(@RequestParam(defaultValue = "130010") String city) {
        return weatherClient.getWeather(city);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
    }
}
