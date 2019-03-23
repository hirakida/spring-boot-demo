package com.example.controller;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse {
    private int statusCode;
    private String reasonPhrase;

    public ApiResponse(HttpStatus status) {
        statusCode = status.value();
        reasonPhrase = status.getReasonPhrase();
    }
}
