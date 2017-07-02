package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;

import com.example.controller.AsyncApiController.Result;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        RestOperations restOperations = new RestTemplateBuilder().build();
        final String host = "http://localhost:8080";

        restOperations.getForObject(host + "/async1", Void.class);
        Result result = restOperations.getForObject(host + "/async3", Result.class);
        log.info("{}", result);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
