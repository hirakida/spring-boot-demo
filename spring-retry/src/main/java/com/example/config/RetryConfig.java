package com.example.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@EnableRetry
@Configuration
public class RetryConfig {
    private static final int MAX_ATTEMPTS = 4;
    private static final int BACK_OFF_PERIOD = 500;

    @Bean
    public RetryTemplate retryTemplate(RetryListenerImpl retryListener) {
        Map<Class<? extends Throwable>, Boolean> exceptions = Map.of(ResourceAccessException.class, true,
                                                                     HttpServerErrorException.class, true,
                                                                     HttpClientErrorException.class, false);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(MAX_ATTEMPTS, exceptions);
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(BACK_OFF_PERIOD);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);
        template.registerListener(retryListener);
        return template;
    }
}
