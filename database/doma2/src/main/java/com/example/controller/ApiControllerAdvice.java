package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.core.DataNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                   HttpStatus.NOT_FOUND.getReasonPhrase());
        return handleExceptionInternal(e, response, null, HttpStatus.OK, request);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private int errorCode;
        private String errorMessage;
    }
}
