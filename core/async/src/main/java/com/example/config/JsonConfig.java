package com.example.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JsonConfig {

    @Bean
    public SimpleModule customModule() {
        SimpleModule module = new SimpleModule("customModule");
        module.addSerializer(LocalDateTime.class, new LocalDateTimeJsonSerializer());
        return module;
    }

    public static class LocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime var1, JsonGenerator var2, SerializerProvider var3)
                throws IOException {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS")
                                                        .withResolverStyle(ResolverStyle.STRICT);
            var2.writeString(var1.format(format));
        }
    }
}
