package com.example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureWireMock
public class ApiControllerTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getWeatherTest() throws IOException {
        String city = "130010";
        String response = "{\"message\": \"result\"}";
        stubFor(WireMock.get(urlEqualTo('/' + city))
                        .willReturn(aResponse().withStatus(200)
                                               .withHeader("Content-Type", APPLICATION_JSON_VALUE)
                                               .withBody(response)));

        JsonNode jsonNode = restTemplate.getForObject("http://localhost:8080/{city}", JsonNode.class, 130010);
        assertThat(jsonNode).isEqualTo(objectMapper.readTree(response));
    }
}
