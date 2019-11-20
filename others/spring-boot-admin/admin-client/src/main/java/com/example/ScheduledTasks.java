package com.example;

import static com.example.WeatherApiClient.DEFAULT_CITY_CODE;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private final WeatherApiClient weatherApiClient;

    public ScheduledTasks(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    @Scheduled(fixedDelay = 5000)
    public void task1() {
        logger.info("{}", LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 60000)
    public void task2() {
        weatherApiClient.getWeather(DEFAULT_CITY_CODE);
    }
}
