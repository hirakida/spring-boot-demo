package com.example.config;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

import com.example.enums.Gender;

public class GenderFormatter implements Formatter<Gender> {

    @Override
    public Gender parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        // convert from String to enum
        return Gender.of(text)
                     .orElseThrow(() -> new ParseException(text, 0));
    }

    @Override
    public String print(Gender gender, Locale locale) {
        // convert from enum to String
        return gender == null ? null : gender.getLabel();
    }
}
