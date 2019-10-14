package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ApiClientTest {
    private static final String URL = "http://weather.livedoor.com";
    @Autowired
    private ApiClient apiClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getWeatherTest_200() throws IOException {
        MockRestServiceServer server = WireMockRestServiceServer.with(restTemplate)
                                                                .baseUrl(URL)
                                                                .stubs("classpath:/stubs/get_weather_200.json")
                                                                .build();

        JsonNode jsonNode = apiClient.getWeather("130010");
        String responseBody = "{\"location\": {\"city\":\"東京\"}}";
        assertThat(jsonNode).isEqualTo(objectMapper.readTree(responseBody));
        server.verify();
    }

    @Test
    public void getWeatherTest_404() throws IOException {
        MockRestServiceServer server = WireMockRestServiceServer.with(restTemplate)
                                                                .baseUrl(URL)
                                                                .stubs("classpath:/stubs/get_weather_404.json")
                                                                .build();
        thrown.expect(HttpClientErrorException.class);

        apiClient.getWeather("0");
        server.verify();
    }
}
