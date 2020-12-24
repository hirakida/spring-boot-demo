package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("production")
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProductionProfileTest {
    @Autowired
    private AppProperties properties;

    @Test
    public void test() {
        assertEquals("production message", properties.getMessage());
    }
}
