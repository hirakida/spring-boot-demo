package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@RefreshScope
@Component
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Validated
@Data
public class AppProperties {
    @NotNull
    private String message1;
    @NotNull
    private String message2;
    private Integer number1;
    private Integer number2;
}
