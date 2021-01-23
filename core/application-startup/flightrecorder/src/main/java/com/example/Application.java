package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .applicationStartup(new FlightRecorderApplicationStartup())
                .run(args);
    }
}
