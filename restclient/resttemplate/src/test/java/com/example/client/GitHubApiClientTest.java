package com.example.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import com.example.model.User;

@RestClientTest(GitHubApiClient.class)
@AutoConfigureWebClient(registerRestTemplate = true)
public class GitHubApiClientTest {
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private GitHubApiClient client;

    @Test
    public void getUser() {
        final String responseBody = "{\"id\":1,\"name\":\"hirakida\"}";
        server.expect(requestTo("https://api.github.com/users/hirakida"))
              .andExpect(method(HttpMethod.GET))
              .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        User user = client.getUser("hirakida");

        assertEquals(1, user.getId());
        assertEquals("hirakida", user.getName());
        server.verify();
    }
}
