package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class JmsArtemisApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsArtemisApplication.class, args);
    }
}
