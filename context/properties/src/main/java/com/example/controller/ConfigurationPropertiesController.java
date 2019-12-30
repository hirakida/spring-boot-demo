package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppProperties;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ConfigurationPropertiesController {
    private final AppProperties properties;

    @GetMapping("/")
    public Response configurationProperties() {
        return new Response(properties.getString().getValue1(),
                            properties.getString().getValue2(),
                            properties.getNumber().getValue1(),
                            properties.getNumber().getValue2());
    }
}
