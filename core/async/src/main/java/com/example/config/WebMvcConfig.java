package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.interceptor.CustomCallableProcessingInterceptor;
import com.example.interceptor.CustomDeferredProcessingInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // Spring MVC管理下のスレッド用
        configurer.registerCallableInterceptors(new CustomCallableProcessingInterceptor());
        // Spring MVC管理外のスレッド用
        configurer.registerDeferredResultInterceptors(new CustomDeferredProcessingInterceptor());
    }
}
