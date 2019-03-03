package com.example.support;

import java.io.IOException;

import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiResponseErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() != Series.SUCCESSFUL;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        // 4xx   HttpClientErrorException
        // 5xx   HttpServerErrorException
        // other RestClientException
        log.error("handleError: {} {}", response.getStatusCode(), response.getStatusText());
        super.handleError(response);
    }
}