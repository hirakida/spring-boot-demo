package com.example.config;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ZoneDateTimeJsonSerializer extends JsonSerializer<ZonedDateTime> {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
                                                                     .withResolverStyle(ResolverStyle.STRICT);

    @Override
    public void serialize(ZonedDateTime zoneDateTime, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(zoneDateTime.format(format));
    }
}
