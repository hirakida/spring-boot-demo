package com.example;

import static com.example.WeatherApiClient.DEFAULT_CITY_CODE;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final WeatherApiClient weatherApiClient;

    @Scheduled(fixedDelay = 5000)
    public void task1() {
      log.info("{}", LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 60000)
    public void task2() {
        log.info("{}", weatherApiClient.getWeather(DEFAULT_CITY_CODE));
    }
}
