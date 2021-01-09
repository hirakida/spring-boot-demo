package com.example;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@RefreshScope
@ConfigurationProperties(prefix = "app")
@Validated
@Data
public class AppProperties {
    @NotNull
    private String message1;
    @NotNull
    private String message2;
    @NotNull
    private Integer number1;
    @NotNull
    private Integer number2;
}
