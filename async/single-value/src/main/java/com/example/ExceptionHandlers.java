package com.example;

import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<Object> handleTimeoutException(TimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
