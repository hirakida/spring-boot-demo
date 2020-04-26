package com.example;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties(prefix = "demo")
@Validated
@Data
public class DemoProperties {
    private boolean enabled = true;
    @NotNull
    private NumberData number;
    @NotNull
    private StringData string;
    @NotNull
    private String ddlAuto;

    @Data
    public static class NumberData {
        /**
         * Min(1).
         */
        @Min(1)
        private long value1 = 1;
        /**
         * Min(0).
         */
        @Min(0)
        private long value2;
    }

    @Data
    public static class StringData {
        /**
         * NotEmpty.
         */
        @NotEmpty
        private String value1;
        /**
         * Optional.
         */
        private String value2 = "ConfigurationProperties default";
    }
}
