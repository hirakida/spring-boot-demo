package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Value;

@ConfigurationProperties(prefix = "app")
@Value
public class AppProperties {
    String message;
}
