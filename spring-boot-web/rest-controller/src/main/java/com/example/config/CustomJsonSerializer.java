package com.example.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomJsonSerializer {

    public static class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime var1, JsonGenerator var2, SerializerProvider var3)
                throws IOException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                                                        .withResolverStyle(ResolverStyle.STRICT);
            var2.writeString(var1.format(format));
        }
    }

    public static class ZonedDateTimeJsonSerializer extends JsonSerializer<ZonedDateTime> {
        @Override
        public void serialize(ZonedDateTime var1, JsonGenerator var2, SerializerProvider var3)
                throws IOException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                                                        .withResolverStyle(ResolverStyle.STRICT);
            var2.writeString(var1.format(format));
        }
    }
}
