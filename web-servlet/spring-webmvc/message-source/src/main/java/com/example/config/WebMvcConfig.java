package com.example.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final MessageSource messageSource;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new MessageSourceResolvableFormatter(messageSource));
    }
}
