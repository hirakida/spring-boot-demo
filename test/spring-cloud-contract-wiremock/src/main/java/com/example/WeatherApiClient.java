package com.example;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

@Component
public class WeatherApiClient {
    static final String BASE_URL = "http://weather.livedoor.com";
    private final RestTemplate restTemplate;

    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Nullable
    public JsonNode getWeather(String city) {
        String uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                                         .path("/forecast/webservice/json/v1")
                                         .queryParam("city", "{city}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, JsonNode.class, city);
    }
}
