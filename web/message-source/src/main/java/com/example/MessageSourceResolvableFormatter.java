package com.example;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.format.Formatter;

public class MessageSourceResolvableFormatter implements Formatter<MessageSourceResolvable> {

    private final MessageSource messageSource;

    public MessageSourceResolvableFormatter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String print(MessageSourceResolvable object, Locale locale) {
        return messageSource.getMessage(object, locale);
    }

    @Override
    public MessageSourceResolvable parse(String str, Locale locale) {
        throw new UnsupportedOperationException();
    }
}
