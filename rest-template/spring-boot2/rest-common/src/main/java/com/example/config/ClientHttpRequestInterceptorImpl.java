package com.example.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHttpRequestInterceptorImpl implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        ClientHttpResponse response = execution.execute(request, body);
        if (response.getStatusCode().series() != Series.SUCCESSFUL) {
            log.error("request: {} {} response: {} {}",
                      request.getMethod(), request.getURI(),
                      response.getStatusCode(),
                      StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
        }
        return response;
    }
}
