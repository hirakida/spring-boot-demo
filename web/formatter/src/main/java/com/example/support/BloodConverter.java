package com.example.support;

import org.springframework.core.convert.converter.Converter;

import com.example.enums.Blood;

public class BloodConverter implements Converter<String, Blood> {

    @Override
    public Blood convert(String code) {
        return Blood.of(code)
                    .orElse(Blood.UNKNOWN);
    }
}
