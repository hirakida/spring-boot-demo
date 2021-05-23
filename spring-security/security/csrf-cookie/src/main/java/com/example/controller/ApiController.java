package com.example.controller;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

    @PostMapping
    public void post(@RequestBody @Validated Form form) {
        log.info("{}", form);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                .collect(toUnmodifiableList());
    }

    @Data
    private static class Form {
        @NotEmpty
        private String lastName;
        @NotEmpty
        private String firstName;
    }

    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private String field;
        private String message;
    }
}
