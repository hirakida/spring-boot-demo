package com.example.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.springframework.util.StringUtils;

import com.example.domain.Gender;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class CustomJsonDeserializer {

    public static class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context)
                throws IOException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                                                        .withResolverStyle(ResolverStyle.STRICT);
            return LocalDateTime.parse(jsonParser.getValueAsString(), format);
        }
    }

    public static class StringTrimJsonDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            String value = StringDeserializer.instance.deserialize(jsonParser, context);
            value = StringUtils.trimWhitespace(value);
            return StringUtils.isEmpty(value) ? null : value;
        }
    }

    public static class GenderDeserializer extends JsonDeserializer<Gender> {
        @Override
        public Gender deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            String value = StringDeserializer.instance.deserialize(jsonParser, context);
            return Gender.of(value)
                         .orElseThrow(() -> new InvalidFormatException(jsonParser,
                                                                       "invalid value.",
                                                                       value,
                                                                       Gender.class));
        }
    }
}
