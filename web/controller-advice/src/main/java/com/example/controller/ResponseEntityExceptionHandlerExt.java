package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ResponseEntityExceptionHandlerExt extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        log.info("handleExceptionInternal");
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e, WebRequest request) {
        log.info("handleResponseStatusException");
        ErrorResponse response = new ErrorResponse(e.getStatus().value(), e.getReason());
        return handleExceptionInternal(e, response, null, e.getStatus(), request);
    }

    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private int code;
        private String message;
    }
}
