package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class ClientHttpRequestInterceptorImpl implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ClientHttpRequestInterceptorImpl.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        final ClientHttpResponse response = execution.execute(request, body);
        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("request: {} {} response: {} {}",
                         request.getMethod(), request.getURI(), response.getStatusCode(),
                         StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        }
        return response;
    }
}
