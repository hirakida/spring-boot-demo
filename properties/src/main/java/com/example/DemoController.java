package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final Environment environment;
    private final DemoProperties properties;
    private final long number1;
    private final long number2;
    private final String str1;
    private final String str2;

    public DemoController(Environment environment,
                          DemoProperties properties,
                          @Value("${demo.number.value1}") long number1,
                          @Value("${demo.number.value2:0}") long number2,
                          @Value("${demo.string.value1}") String str1,
                          @Value("${demo.string.value2:@Value default}") String str2) {
        this.environment = environment;
        this.properties = properties;
        this.number1 = number1;
        this.number2 = number2;
        this.str1 = str1;
        this.str2 = str2;
    }

    @GetMapping("/")
    public Response configurationProperties() {
        return new Response(properties.getNumber().getValue1(),
                            properties.getNumber().getValue2(),
                            properties.getString().getValue1(),
                            properties.getString().getValue2());
    }

    @GetMapping("/environment")
    public Response environment() {
        long number1 = environment.getProperty("demo.number.value1", Long.class, 0L);
        long number2 = environment.getProperty("demo.number.value2", Long.class, 0L);
        String str1 = environment.getProperty("demo.string.value1");
        String str2 = environment.getProperty("demo.string.value2", String.class, "Environment default");
        return new Response(number1, number2, str1, str2);
    }

    @GetMapping("/value")
    public Response value() {
        return new Response(number1, number2, str1, str2);
    }

    @lombok.Value
    public static class Response {
        long number1;
        long number2;
        String str1;
        String str2;
    }
}
