package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppProperties;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PropertiesController {
    private final AppProperties properties;

    @GetMapping("/")
    public Response properties() {
        return new Response(properties.getNumber().getValue1(),
                            properties.getNumber().getValue2(),
                            properties.getString().getValue1(),
                            properties.getString().getValue2());
    }
}
