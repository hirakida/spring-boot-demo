package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.interceptor.AsyncHandlerInterceptorImpl;
import com.example.interceptor.CallableProcessingInterceptorImpl;
import com.example.interceptor.DeferredResultProcessingInterceptorImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final AsyncTaskExecutor asyncTaskExecutor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(asyncTaskExecutor);  // For Callable and StreamingResponseBody
        configurer.registerCallableInterceptors(new CallableProcessingInterceptorImpl());
        configurer.registerDeferredResultInterceptors(new DeferredResultProcessingInterceptorImpl());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AsyncHandlerInterceptorImpl());
    }
}
