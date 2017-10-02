package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Primary
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

    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        factory.setWriteTimeout(5000);
        return factory;
    }

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
//                    .detectRequestFactory(false)    // 自動検出
                    .uriTemplateHandler(uriTemplateHandler)
                    .setConnectTimeout(5000)
                    .setReadTimeout(5000)
                    .configure(restTemplate);
        }
    }
}
