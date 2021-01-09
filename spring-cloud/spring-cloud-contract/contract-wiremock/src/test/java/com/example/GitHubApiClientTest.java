package com.example;

import static com.example.GitHubApiClient.BASE_URL;
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
public class GitHubApiClientTest {
    @Autowired
    private GitHubApiClient client;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getUserTest_200() throws Exception {
        MockRestServiceServer server = WireMockRestServiceServer.with(restTemplate)
                                                                .baseUrl(BASE_URL)
                                                                .stubs("classpath:/stubs/get_user_200.json")
                                                                .build();
        JsonNode expected = objectMapper.readTree("{\"login\":\"hirakida\",\"type\":\"User\"}");
        JsonNode actual = client.getUser("hirakida");

        assertEquals(expected, actual);
        server.verify();
    }

    @Test
    public void getUserTest_404() throws IOException {
        MockRestServiceServer server = WireMockRestServiceServer.with(restTemplate)
                                                                .baseUrl(BASE_URL)
                                                                .stubs("classpath:/stubs/get_user_404.json")
                                                                .build();

        assertThrows(HttpClientErrorException.class, () -> client.getUser("hirakida_"));
        server.verify();
    }
}
