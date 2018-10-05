package com.github.hirakida.spring.boot;

import org.springframework.stereotype.Component;

@Component
public class HelloBean {

    private static final String DEFAULT_MESSAGE = "spring boot";
    private final HelloProperties helloProperties;

    public HelloBean(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String getMessage() {
        String message = helloProperties.getMessage() != null
                         ? helloProperties.getMessage() : DEFAULT_MESSAGE;
        return String.format("Hello, %s!", message);
    }
}
