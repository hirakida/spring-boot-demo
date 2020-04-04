package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String[] INCLUDE_PATTERNS = { "/**" };
    private static final String[] EXCLUDE_PATTERNS = { "/v2/**", "/error" };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorImpl())
                .addPathPatterns(INCLUDE_PATTERNS)
                .excludePathPatterns(EXCLUDE_PATTERNS);
    }

    //    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(INCLUDE_PATTERNS, EXCLUDE_PATTERNS, new HandlerInterceptorImpl());
    }
}
