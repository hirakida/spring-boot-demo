package com.example.config;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import com.example.enums.Gender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenderFormatter implements Formatter<Gender> {

    /**
     * Convert from String to Enum
     */
    @Override
    public Gender parse(String code, Locale locale) throws ParseException {
        log.info("parse: {}", code);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return Gender.of(code)
                     .orElseThrow(() -> new ParseException(code, 0));
    }

    /**
     * Convert from Enum to String
     */
    @Override
    public String print(Gender gender, Locale locale) {
        log.info("print: {}", gender);
        return gender == null ? null : gender.getLabel();
    }
}
