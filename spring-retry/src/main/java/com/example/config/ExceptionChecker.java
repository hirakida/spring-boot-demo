package com.example.config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExceptionChecker {
    public boolean shouldRetry(Throwable t) {
        log.info("{}", t.getMessage());
        return t instanceof HttpServerErrorException
               && ((HttpServerErrorException) t).getStatusCode() == HttpStatus.GATEWAY_TIMEOUT;
    }
}
