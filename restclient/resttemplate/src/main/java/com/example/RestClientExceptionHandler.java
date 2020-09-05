package com.example;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestClientExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                             HttpHeaders headers, HttpStatus status,
                                                             WebRequest request) {
        log.warn("{}", request, ex);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e,
                                                                 WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(e.getStatusCode()),
                                       HttpHeaders.EMPTY, e.getStatusCode(), request);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException e,
                                                                 WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR),
                                       HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Object> handleRestClientException(RestClientException e,
                                                            WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR),
                                       HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Object> handleResourceAccessException(ResourceAccessException e,
                                                                WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(HttpStatus.REQUEST_TIMEOUT),
                                       HttpHeaders.EMPTY, HttpStatus.REQUEST_TIMEOUT, request);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e,
                                                               WebRequest request) {
        return handleExceptionInternal(e, new ErrorResponse(HttpStatus.NOT_FOUND),
                                       HttpHeaders.EMPTY, HttpStatus.NOT_FOUND, request);
    }

    @Value
    public static class ErrorResponse {
        int statusCode;
        String message;

        public ErrorResponse(HttpStatus httpStatus) {
            statusCode = httpStatus.value();
            message = httpStatus.getReasonPhrase();
        }
    }
}
