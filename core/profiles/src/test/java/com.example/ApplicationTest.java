package com.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ApplicationTest {
    @Autowired
    private Environment environment;
    @Autowired
    private AppProperties properties;

    @Test
    public void test() {
        assertArrayEquals(new String[] { "test" }, environment.getActiveProfiles());
        assertEquals("test message", properties.getMessage());
    }
}
