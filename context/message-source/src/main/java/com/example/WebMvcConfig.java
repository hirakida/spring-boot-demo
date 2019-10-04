package com.example;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
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

    @RequiredArgsConstructor
    private static class MessageSourceResolvableFormatter implements Formatter<MessageSourceResolvable> {
        private final MessageSource messageSource;

        @Override
        public String print(MessageSourceResolvable object, Locale locale) {
            return messageSource.getMessage(object, locale);
        }

        @Override
        public MessageSourceResolvable parse(String str, Locale locale) {
            throw new UnsupportedOperationException();
        }
    }
}
