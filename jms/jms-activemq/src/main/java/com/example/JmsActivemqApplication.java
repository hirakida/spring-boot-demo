package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class JmsActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsActivemqApplication.class, args);
    }
}
