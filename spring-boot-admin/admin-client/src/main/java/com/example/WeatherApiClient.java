package com.example;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@CacheConfig(cacheNames = "cache1")
public class WeatherApiClient {
    private static final String URL = "http://weather.livedoor.com/forecast/webservice/json/v1";
    public static final String DEFAULT_CITY_CODE = "130010";
    private final RestTemplate restTemplate;

    public WeatherApiClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @Cacheable(key = "'weather:' + #cityCode")
    public Map<?, ?> getWeather(String city) {
        String uri = UriComponentsBuilder.fromHttpUrl(URL)
                                         .queryParam("city", "{city}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, Map.class, city);
    }
}
