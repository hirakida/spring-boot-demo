package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JmsArtemisApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsArtemisApplication.class, args);
    }
}
