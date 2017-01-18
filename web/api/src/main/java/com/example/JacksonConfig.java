package com.example;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.example.util.CustomJsonDeserializer.LocalDateTimeJsonDeserializer;
import com.example.util.CustomJsonDeserializer.StringTrimmerJsonDeserializer;
import com.example.util.CustomJsonSerializer.LocalDateTimeJsonSerializer;
import com.example.util.CustomJsonSerializer.ZonedDateTimeJsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return Jackson2ObjectMapperBuilder.json()
                                          // camel case to lower snake case
                                          .propertyNamingStrategy(SNAKE_CASE)
                                          // valueがnullの項目は表示されなくなる
//                                          .serializationInclusion(JsonInclude.Include.NON_NULL)
//                                          .indentOutput(true)
                                          .modules(new Jdk8Module(), customModule());
    }

    @Bean
    public SimpleModule customModule() {
        SimpleModule module = new SimpleModule("customModule");
        module.addDeserializer(String.class, new StringTrimmerJsonDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeJsonSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeJsonDeserializer());
        module.addSerializer(ZonedDateTime.class, new ZonedDateTimeJsonSerializer());
//        module.addDeserializer(Gender.class, new GenderDeserializer());
        return module;
    }
}
