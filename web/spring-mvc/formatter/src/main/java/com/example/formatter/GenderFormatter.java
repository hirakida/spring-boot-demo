package com.example.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.jetbrains.annotations.Nullable;
import org.springframework.format.Formatter;
import org.springframework.util.ObjectUtils;

import com.example.enums.Gender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenderFormatter implements Formatter<Gender> {
    @Nullable
    @Override
    public Gender parse(String code, Locale locale) throws ParseException {
        log.info("parse: {}", code);
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        return Gender.of(code)
                     .orElseThrow(() -> new ParseException(code, 0));
    }

    @Override
    public String print(Gender gender, Locale locale) {
        log.info("print: {}", gender);
        return gender.getLabel();
    }
}
