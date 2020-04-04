package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.formatter.BloodConverter;
import com.example.formatter.GenderFormatter;
import com.example.formatter.LocalDateTimeFormatter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new GenderFormatter());
        registry.addFormatter(new LocalDateTimeFormatter());
        registry.addConverter(new BloodConverter());
    }
}
