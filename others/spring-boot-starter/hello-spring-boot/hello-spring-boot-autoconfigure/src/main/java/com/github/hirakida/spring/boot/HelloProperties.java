package com.github.hirakida.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = HelloProperties.HELLO_PREFIX)
public class HelloProperties {
    public static final String HELLO_PREFIX = "hello";
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
