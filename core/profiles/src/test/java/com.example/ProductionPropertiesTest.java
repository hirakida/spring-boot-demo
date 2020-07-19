package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("production")
@SpringBootTest(classes = TestConfig.class)
public class ProductionPropertiesTest {
    @Autowired
    private AppProperties properties;

    @Test
    public void test() {
        Assertions.assertEquals("production message", properties.getMessage());
    }
}
