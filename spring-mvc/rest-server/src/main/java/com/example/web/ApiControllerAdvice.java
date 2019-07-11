package com.example.web;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<Void> handleResponseStatusException(ResponseStatusException e) {
        log.info("{}", e.getMessage(), e);
        return ResponseEntity.status(e.getStatus()).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequestException(RuntimeException e) {
        log.warn("{}", e.getMessage(), e);
    }

    @ExceptionHandler({
            EntityNotFoundException.class,
            NoSuchElementException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFoundException(RuntimeException e) {
        log.warn("{}", e.getMessage(), e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("{}", e.getMessage(), e);
    }
}
