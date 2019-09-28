package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class ApiController {
    private final String str1;
    private final String str2;
    private final long number1;
    private final long number2;
    private final Environment environment;
    private final AppProperties properties;

    public ApiController(@Value("${app.string.value1}") String str1,
                         @Value("${app.string.value2:@Value default}") String str2,
                         @Value("${app.number.value1}") long number1,
                         @Value("${app.number.value2:0}") long number2,
                         Environment environment,
                         AppProperties properties) {
        this.str1 = str1;
        this.str2 = str2;
        this.number1 = number1;
        this.number2 = number2;
        this.environment = environment;
        this.properties = properties;
    }

    @GetMapping("/properties")
    public Response properties() {
        return new Response(properties.getString().getValue1(),
                            properties.getString().getValue2(),
                            properties.getNumber().getValue1(),
                            properties.getNumber().getValue2());
    }

    @GetMapping("/value")
    public Response value() {
        return new Response(str1, str2, number1, number2);
    }

    @GetMapping("/environment")
    public Response environment() {
        return new Response(environment.getProperty("app.string.value1"),
                            environment.getProperty("app.string.value2", String.class, "Environment default"),
                            environment.getProperty("app.number.value1", Long.class, 0L),
                            environment.getProperty("app.number.value2", Long.class, 0L));
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private String str1;
        private String str2;
        private long number1;
        private long number2;
    }
}
