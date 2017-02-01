package com.example;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                                                                 .withResolverStyle(ResolverStyle.STRICT);

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return LocalDateTime.parse(text, formatter);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime == null ? null : localDateTime.format(formatter);
    }
}
