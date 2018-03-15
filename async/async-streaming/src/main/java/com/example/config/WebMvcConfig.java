package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.interceptor.MyAsyncHandlerInterceptor;
import com.example.interceptor.MyCallableProcessingInterceptor;
import com.example.interceptor.MyDeferredResultProcessingInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final AsyncTaskExecutor asyncTaskExecutor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // for StreamingResponseBody
        configurer.setTaskExecutor(asyncTaskExecutor);
        configurer.registerCallableInterceptors(new MyCallableProcessingInterceptor());
        // for ResponseBodyEmitter, SseEmitter
        configurer.registerDeferredResultInterceptors(new MyDeferredResultProcessingInterceptor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyAsyncHandlerInterceptor());
    }
}
