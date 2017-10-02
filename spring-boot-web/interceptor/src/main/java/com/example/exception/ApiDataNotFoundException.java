package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiDataNotFoundException extends RuntimeException {
    public ApiDataNotFoundException(String message) {
        super(message);
    }
}
