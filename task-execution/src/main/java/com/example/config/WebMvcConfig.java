package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.interceptor.MyAsyncHandlerInterceptor;
import com.example.interceptor.MyCallableProcessingInterceptor;
import com.example.interceptor.MyDeferredResultProcessingInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AsyncTaskExecutor asyncTaskExecutor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(asyncTaskExecutor);  // for Callable and StreamingResponseBody
        configurer.registerCallableInterceptors(new MyCallableProcessingInterceptor());
        configurer.registerDeferredResultInterceptors(new MyDeferredResultProcessingInterceptor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyAsyncHandlerInterceptor());
    }
}
