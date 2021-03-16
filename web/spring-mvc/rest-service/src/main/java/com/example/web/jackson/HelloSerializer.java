package com.example.web.jackson;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.boot.jackson.JsonComponent;

import com.example.web.model.HelloResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class HelloSerializer extends JsonSerializer<HelloResponse> {
    @Override
    public void serialize(HelloResponse value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeStringField("message", value.getMessage());
        gen.writeObjectField("datetime", LocalDateTime.now());
        gen.writeEndObject();
    }
}
