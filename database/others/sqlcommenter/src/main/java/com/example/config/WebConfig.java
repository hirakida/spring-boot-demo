package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.cloud.sqlcommenter.interceptors.SpringSQLCommenterInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sqlInterceptor());
    }

    @Bean
    public SpringSQLCommenterInterceptor sqlInterceptor() {
        return new SpringSQLCommenterInterceptor();
    }
}
