package com.example.controller;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler({ EmptyResultDataAccessException.class, NoSuchElementException.class })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
