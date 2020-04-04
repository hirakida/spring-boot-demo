package com.example;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class HttpStatusSerializer extends JsonSerializer<HttpStatus> {

    @Override
    public void serialize(HttpStatus value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("statusCode");
        gen.writeNumber(value.value());
        gen.writeFieldName("reasonPhrase");
        gen.writeString(value.getReasonPhrase());
        gen.writeEndObject();
    }
}
