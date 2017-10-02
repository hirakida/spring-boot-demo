package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.interceptor.MyAsyncHandlerInterceptor;
import com.example.interceptor.MyCallableProcessingInterceptor;
import com.example.interceptor.MyDeferredProcessingInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    final AsyncTaskExecutor asyncTaskExecutor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // Spring MVC管理下のthreadもthread poolを使用する
        configurer.setTaskExecutor(asyncTaskExecutor);
//        configurer.setDefaultTimeout(10000);  // application.ymlから指定できる
        configurer.registerCallableInterceptors(new MyCallableProcessingInterceptor());
        configurer.registerDeferredResultInterceptors(new MyDeferredProcessingInterceptor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyHandlerInterceptor());
        registry.addInterceptor(new MyAsyncHandlerInterceptor());
    }
}
