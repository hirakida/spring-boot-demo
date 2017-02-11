package com.example;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@Validated
@Data
public class AppProperties {
    // validation errorの場合は、DIコンテナ登録時にBindExceptionがthrowされる
    @NotNull
    private AppNumber number;
    @NotNull
    private AppString string;

    @Data
    public static class AppNumber {
        @Min(1)
        private int value1;
        @Min(1)
        private int value2;
    }

    @Data
    public static class AppString {
        @NotEmpty
        private String value1;
        @NotEmpty
        private String value2;
    }
}
