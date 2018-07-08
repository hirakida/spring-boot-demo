package com.hirakida.spring.boot;

import java.time.LocalDateTime;

public class HelloResponse {
    private final String message;
    private final String now;

    HelloResponse(String message, LocalDateTime localDateTime) {
        this.message = message;
        now = localDateTime.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getNow() {
        return now;
    }
}
