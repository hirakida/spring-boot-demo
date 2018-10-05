package com.example.config;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                             .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Convert from String to Enum
     */
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        log.info("parse: {}", text);
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        try {
            return LocalDateTime.parse(text, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.toString(), 0);
        }
    }

    /**
     * Convert from Enum to String
     */
    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        log.info("print: {}", localDateTime);
        return localDateTime == null ? null : localDateTime.format(FORMATTER);
    }
}
