package com.example.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.client.model.Weather;

@Component
public class WeatherClient {
    private final RestTemplate restTemplate;

    public WeatherClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public Weather getWeather(String city) {
        String uri = UriComponentsBuilder.fromHttpUrl("http://weather.livedoor.com/forecast/webservice/json/v1")
                                         .queryParam("city", "{city}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, Weather.class, city);
    }
}
