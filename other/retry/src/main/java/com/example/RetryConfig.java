package com.example;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

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
                                HttpClientErrorException.class, false);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(MAX_ATTEMPTS, exceptions);
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(500);

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);
        template.setListeners(ArrayUtils.toArray(new RetryListenerImpl()));
        return template;
    }

    public static class RetryListenerImpl implements RetryListener {
        @Override
        public <T, E extends Throwable> void close(RetryContext context,
                                                   RetryCallback<T, E> callback,
                                                   Throwable throwable) {
            if (throwable != null) {
                log.info("close {} {} {}", context, callback, throwable.getClass().getCanonicalName());
            }
        }

        @Override
        public <T, E extends Throwable> void onError(RetryContext context,
                                                     RetryCallback<T, E> callback,
                                                     Throwable throwable) {
            if (throwable != null) {
//                log.info("onError {} {} {}", context, callback, throwable.getClass().getCanonicalName());
            }
        }

        @Override
        public <T, E extends Throwable> boolean open(RetryContext context,
                                                     RetryCallback<T, E> callback) {
            log.info("open {} {}", context, callback);
            return true;
        }
    }
}
