package com.example.config;

import static java.util.Collections.singletonList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
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
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Bean(name = "defaultRestOperations")
    @Primary
    public RestOperations restOperations() {
        return new RestTemplateBuilder()
                .additionalCustomizers(new RestTemplateCustomizerImpl())
                .requestFactory(httpComponentsClientHttpRequestFactory())
                .build();
    }

    @Bean(name = "okHttp3RestOperations")
    public RestOperations okHttp3RestOperations() {
        return new RestTemplateBuilder()
                .additionalCustomizers(new RestTemplateCustomizerImpl())
                .requestFactory(okHttp3ClientHttpRequestFactory())
                .build();
    }

    @Bean(name = "okHttpRestOperations")
    public RestOperations okHttpRestOperations() {
        return new RestTemplateBuilder()
                .additionalCustomizers(new RestTemplateCustomizerImpl())
                .requestFactory(okHttpClientHttpRequestFactory())
                .build();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setConnectionRequestTimeout(5000);
        return factory;
    }

    @Bean
    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setWriteTimeout(5000);
        return factory;
    }

    @Bean
    public OkHttpClientHttpRequestFactory okHttpClientHttpRequestFactory() {
        OkHttpClientHttpRequestFactory factory = new OkHttpClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setWriteTimeout(5000);
        return factory;
    }

    public static class RestTemplateCustomizerImpl implements RestTemplateCustomizer {
        @Override
        public void customize(RestTemplate restTemplate) {
            DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
            uriTemplateHandler.setStrictEncoding(true);
            new RestTemplateBuilder()
                    .defaultMessageConverters()
                    .detectRequestFactory(false)    // 2つのライブラリを使う場合は自動検出をoffにする
                    .uriTemplateHandler(uriTemplateHandler)
                    .errorHandler(new CustomResponseErrorHandler())
                    .interceptors(singletonList(new CustomClientHttpRequestInterceptor()))
                    .setConnectTimeout(5000)
                    .setReadTimeout(5000)
                    .configure(restTemplate);
        }
    }

    private static class CustomResponseErrorHandler extends DefaultResponseErrorHandler {
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

    private static class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request,
                                            byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            ClientHttpResponse response = execution.execute(request, body);
            if (response.getStatusCode().series() != Series.SUCCESSFUL) {
                log.error("request: {} {} response: {} {}",
                          request.getMethod(), request.getURI(),
                          response.getStatusCode(),
                          IOUtils.toString(response.getBody(), StandardCharsets.UTF_8));
            }
            return response;
        }
    }
}
