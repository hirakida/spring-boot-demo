package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(classes = TestConfig.class)
public class DevPropertiesTest {
    @Autowired
    private AppProperties properties;

    @Test
    public void test() {
        Assertions.assertEquals("dev message", properties.getMessage());
    }
}
