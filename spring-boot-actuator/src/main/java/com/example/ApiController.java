package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.micrometer.core.annotation.Timed;

@RestController
public class ApiController {
    private final RestTemplate restTemplate;

    public ApiController(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @GetMapping("/now")
    @Timed("now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @GetMapping("/weather")
    @Timed("weather")
    public Map<?, ?> weather(@RequestParam(defaultValue = "130010") String city) {
        String uri = UriComponentsBuilder.fromHttpUrl("http://weather.livedoor.com/forecast/webservice/json/v1")
                                         .queryParam("city", "{city}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, Map.class, city);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
    }
}
