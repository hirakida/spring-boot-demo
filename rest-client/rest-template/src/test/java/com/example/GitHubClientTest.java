package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import com.example.model.User;

@RestClientTest(GitHubClient.class)
class GitHubClientTest {
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private GitHubClient client;

    @Test
    void getUser() {
        final String responseBody = "{\"id\":1,\"name\":\"hirakida\"}";
        server.expect(requestTo("https://api.github.com/users/hirakida"))
              .andExpect(method(HttpMethod.GET))
              .andRespond(withSuccess()
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .body(responseBody));

        final User user = client.getUser("hirakida");

        assertEquals(1, user.id());
        assertEquals("hirakida", user.name());
        server.verify();
    }
}
