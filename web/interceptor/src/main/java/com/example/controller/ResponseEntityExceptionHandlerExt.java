package com.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.exception.ApiDataNotFoundException;

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

    @ExceptionHandler(ApiDataNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ApiDataNotFoundException e, WebRequest request) {
        log.info("handleNotFoundException");
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                                                   HttpStatus.NOT_FOUND.getReasonPhrase());
        return handleExceptionInternal(e, response, null, HttpStatus.NOT_FOUND, request);
    }

    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private int code;
        private String message;
    }
}
