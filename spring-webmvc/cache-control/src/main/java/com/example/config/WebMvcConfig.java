package com.example.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webContentInterceptor());
    }

    @Bean
    public WebContentInterceptor webContentInterceptor() {
        Properties mappings = new Properties();
        // seconds
        mappings.setProperty("/datetime", "10");
        mappings.setProperty("/js/*.js", "3600");
        mappings.setProperty("/css/*.css", "3600");

        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheMappings(mappings);
        return interceptor;
    }
}
