package com.example.formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.jetbrains.annotations.Nullable;
import org.springframework.format.Formatter;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    @Nullable
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        log.info("parse: {}", text);
        if (ObjectUtils.isEmpty(text)) {
            return null;
        }
        try {
            return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        log.info("print: {}", localDateTime);
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
