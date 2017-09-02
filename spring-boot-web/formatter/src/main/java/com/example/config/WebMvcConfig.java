package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new GenderFormatter());
        registry.addFormatter(new LocalDateTimeFormatter());
        registry.addConverter(new BloodConverter());
    }
}
