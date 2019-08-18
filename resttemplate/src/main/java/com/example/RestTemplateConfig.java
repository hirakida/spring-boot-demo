package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Bean
    public RestOperations restOperations(RestTemplateBuilder restTemplateBuilder) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setConnectionRequestTimeout(5000);

        return restTemplateBuilder
                .requestFactory(() -> factory)
                .errorHandler(new ResponseErrorHandlerExt())
                .interceptors(List.of(new ClientHttpRequestInterceptorImpl()))
                .build();
    }

    @Bean
    @Primary
    public RestOperations okHttpRestOperations(RestTemplateBuilder restTemplateBuilder) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();

        return restTemplateBuilder
                .requestFactory(() -> new OkHttp3ClientHttpRequestFactory(client))
                .errorHandler(new ResponseErrorHandlerExt())
                .interceptors(List.of(new ClientHttpRequestInterceptorImpl()))
                .build();
    }

    private static class ResponseErrorHandlerExt extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            // 4xx   HttpClientErrorException
            // 5xx   HttpServerErrorException
            // other RestClientException
            log.error("handleError: {} {}", response.getStatusCode(), response.getStatusText());
            super.handleError(response);
        }
    }

    private static class ClientHttpRequestInterceptorImpl implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            ClientHttpResponse response = execution.execute(request, body);
            if (response.getStatusCode().series() != Series.SUCCESSFUL) {
                log.error("request: {} {} response: {} {}",
                          request.getMethod(), request.getURI(), response.getStatusCode(),
                          StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));
            }
            return response;
        }
    }
}
