package com.example;

import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.glassdoor.planout4j.config.ConfFileLoader;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty(ConfFileLoader.P4J_CONF_FILE,
                           Paths.get("conf/planout4j.conf").toString());
        SpringApplication.run(Application.class, args);
    }
}
