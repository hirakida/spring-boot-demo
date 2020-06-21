package com.example;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
