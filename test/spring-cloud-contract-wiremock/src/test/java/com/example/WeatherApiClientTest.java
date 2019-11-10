package com.example;

import static com.example.WeatherApiClient.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class WeatherApiClientTest {
    @Autowired
    private WeatherApiClient weatherApiClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getWeatherTest_200() throws IOException {
        MockRestServiceServer server = WireMockRestServiceServer.with(restTemplate)
                                                                .baseUrl(BASE_URL)
                                                                .stubs("classpath:/stubs/get_weather_200.json")
                                                                .build();

        JsonNode jsonNode = weatherApiClient.getWeather("130010");
        String responseBody = "{\"location\": {\"city\":\"東京\"}}";
        assertEquals(jsonNode, objectMapper.readTree(responseBody));
        server.verify();
    }

    @Test
    public void getWeatherTest_404() throws IOException {
        MockRestServiceServer server = WireMockRestServiceServer.with(restTemplate)
                                                                .baseUrl(BASE_URL)
                                                                .stubs("classpath:/stubs/get_weather_404.json")
                                                                .build();

        assertThrows(HttpClientErrorException.class, () -> weatherApiClient.getWeather("0"));
        server.verify();
    }
}
