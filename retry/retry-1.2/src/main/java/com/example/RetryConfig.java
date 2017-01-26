package com.example;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import com.google.common.collect.ImmutableMap;

import lombok.extern.slf4j.Slf4j;

@EnableRetry
@Configuration
@Slf4j
public class RetryConfig {

    private static final int MAX_ATTEMPTS = 4;

    @Bean
    public RetryTemplate retryTemplate() {
        Map<Class<? extends Throwable>, Boolean> exceptions =
                ImmutableMap.of(HttpServerErrorException.class, true,
                                HttpClientErrorException.class, false,
                                RestClientException.class, false);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(MAX_ATTEMPTS, exceptions);
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(500);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);
        return template;
    }

    @Bean
    public RetryExpressionHelper retryExpressionHelper() {
        return new RetryExpressionHelper();
    }

    public static class RetryExpressionHelper {
        // exceptionExpressionでは、exceptionが引数で受け取れる
        public boolean shouldRetry(Throwable t) {
            return t instanceof HttpServerErrorException
                   && ((HttpServerErrorException) t).getRawStatusCode() == HttpStatus.GATEWAY_TIMEOUT.value();
        }
    }
}
