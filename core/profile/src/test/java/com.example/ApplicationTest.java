package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private Environment environment;

    @Test
    public void test() {
        Assertions.assertArrayEquals(new String[] { "test" }, environment.getActiveProfiles());
        Assertions.assertEquals("test message", environment.getProperty("app.message"));
    }
}
