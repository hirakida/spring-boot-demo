package com.example.config;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseErrorHandlerExt extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // 4xx   HttpClientErrorException
        // 5xx   HttpServerErrorException
        // other RestClientException
        log.error("handleError: {} {}", response.getStatusCode(), response.getStatusText());
        super.handleError(response);
    }
}
