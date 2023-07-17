package com.example;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class HelloJsonSerializer extends JsonSerializer<HelloResponse> {
    @Override
    public void serialize(HelloResponse value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeStringField("message", value.message());
        gen.writeObjectField("datetime", LocalDateTime.now());
        gen.writeEndObject();
    }
}
