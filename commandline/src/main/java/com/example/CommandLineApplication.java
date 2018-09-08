package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandLineApplication {

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(CommandLineApplication.class, args)));
    }
}
