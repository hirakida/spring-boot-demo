package com.github.hirakida.spring.boot;

public class HelloBean {
    private static final String DEFAULT_MESSAGE = "Hello, spring boot!";
    private final HelloProperties helloProperties;

    public HelloBean(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String getMessage() {
        return helloProperties.getMessage() != null ? helloProperties.getMessage() : DEFAULT_MESSAGE;
    }
}
