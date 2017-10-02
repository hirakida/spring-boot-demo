package com.example.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.AsyncClientHttpRequestExecution;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.DefaultResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AsyncRestTemplateConfig {

    @Bean
    public AsyncRestOperations asyncRestOperations() {
        AsyncRestTemplate template = new AsyncRestTemplate();
        template.setErrorHandler(new CustomResponseErrorHandler());
        template.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptorImpl()));
        template.setAsyncRequestFactory(httpComponentsAsyncClientHttpRequestFactory());
        return template;
    }

    @Bean
    public HttpComponentsAsyncClientHttpRequestFactory httpComponentsAsyncClientHttpRequestFactory() {
        HttpComponentsAsyncClientHttpRequestFactory factory = new HttpComponentsAsyncClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setConnectionRequestTimeout(5000);
        return factory;
    }

    private static class CustomResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode().series() != Series.SUCCESSFUL;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            log.error("handleError: {} {}", response.getStatusCode(), response.getStatusText());
            super.handleError(response);
        }
    }

    private static class ClientHttpRequestInterceptorImpl implements AsyncClientHttpRequestInterceptor {
        @Override
        public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request,
                                                              byte[] body,
                                                              AsyncClientHttpRequestExecution execution)
                throws IOException {
            ListenableFuture<ClientHttpResponse> future = execution.executeAsync(request, body);
            log.info("intercept: {}", future);
            return future;
        }
    }
}
