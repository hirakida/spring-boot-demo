package com.example;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

public class GenderFormatter implements Formatter<Gender> {

    /**
     * codeの文字列をenumに変換
     */
    @Override
    public Gender parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return Gender.of(text)
                     .orElseThrow(() -> new ParseException(text, 0));
    }

    /**
     * enumをlabelに変換
     */
    @Override
    public String print(Gender gender, Locale locale) {
        return gender == null ? null : gender.getLabel();
    }
}
