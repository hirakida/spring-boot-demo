package com.example.web.formatter;

import org.springframework.core.convert.converter.Converter;

import com.example.web.enums.Blood;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BloodConverter implements Converter<String, Blood> {

    @Override
    public Blood convert(String name) {
        log.info("convert: {}", name);
        try {
            return Blood.valueOf(name);
        } catch (IllegalArgumentException e) {
            return Blood.UNKNOWN;
        }
    }
}
