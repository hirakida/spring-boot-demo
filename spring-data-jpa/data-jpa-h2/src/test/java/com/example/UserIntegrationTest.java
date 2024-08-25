package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:/testdata.sql")
class UserIntegrationTest {
    private static final ParameterizedTypeReference<List<User>> USES_TYPE =
            new ParameterizedTypeReference<>() {};
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void findByName() throws Exception {
        final String uri = UriComponentsBuilder.fromPath("/users")
                                               .queryParam("name", "user7")
                                               .toUriString();
        final var request = RequestEntity.get(new URI(uri)).build();

        final List<User> actual = restTemplate.exchange(request, USES_TYPE).getBody();
        assertNotNull(actual);
        assertEquals(1, actual.size());
        assertEquals("user7", actual.get(0).getName());
        assertEquals(LocalDateTime.parse("2020-12-01T00:00:00"), actual.get(0).getCreatedAt());
    }
}
