package com.example.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.mapping.MappingException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntityMapperImpl implements EntityMapper {
    private static final TypeReference<HashMap<String, Object>> TYPE_REFERENCE = new TypeReference<>() {};
    private final ObjectMapper objectMapper;

    @Override
    public String mapToString(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    @Override
    public Map<String, Object> mapObject(Object source) {
        try {
            return objectMapper.readValue(mapToString(source), TYPE_REFERENCE);
        } catch (IOException e) {
            throw new MappingException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
        return objectMapper.readValue(source, clazz);
    }

    @Override
    public <T> T readObject(Map<String, Object> source, Class<T> targetType) {
        try {
            return mapToObject(mapToString(source), targetType);
        } catch (IOException e) {
            throw new MappingException(e.getMessage(), e);
        }
    }
}
