package com.example.properties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties(prefix = "app")
@Validated
@Data
public class AppProperties {
    private boolean enabled = true;
    private Mode mode1;
    private String mode2;
    @NotNull
    private Test1 test1;
    @NotNull
    @NestedConfigurationProperty
    private Test2 test2;

    @Data
    public static class Test1 {
        /**
         * Min(1).
         */
        @Min(1)
        private long value1 = 1;

        @Min(0)
        private long value2;

        /**
         * NotEmpty.
         */
        @NotEmpty
        private String text1;

        /**
         * Optional.
         */
        private String text2 = "@ConfigurationProperties";
    }

    public enum Mode {
        /**
         * Mode 1.
         */
        MODE1,

        /**
         * Mode 2.
         */
        MODE2,

        /**
         * Mode 3.
         */
        MODE3
    }
}
