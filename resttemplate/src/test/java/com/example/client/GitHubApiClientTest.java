package com.example.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class GitHubApiClientTest {
    private MockRestServiceServer server;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GitHubApiClient gitHubApiClient;

    @BeforeEach
    public void setUp() {
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void getUserTest() {
        final String responseBody = "{\"id\":1,\"name\":\"hirakida\"}";
        server.expect(requestTo("https://api.github.com/users/hirakida"))
              .andExpect(method(HttpMethod.GET))
              .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        Optional<User> response = gitHubApiClient.getUser("hirakida");

        assertTrue(response.isPresent());
        User user = response.get();
        assertEquals(1, user.getId());
        assertEquals("hirakida", user.getName());
        server.verify();
    }
}
