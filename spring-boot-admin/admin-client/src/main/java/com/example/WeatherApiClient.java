package com.example;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@CacheConfig(cacheNames = "default")
public class WeatherApiClient {
    public static final String DEFAULT_CITY_CODE = "130010";
    private final RestTemplate restTemplate;

    public WeatherApiClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @Cacheable(key = "'weather:' + #cityCode")
    public Map<?, ?> getWeather(String cityCode) {
        String uri = UriComponentsBuilder.fromHttpUrl("http://weather.livedoor.com/forecast/webservice/json/v1")
                                         .queryParam("city", "{city}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, Map.class, cityCode);
    }
}
