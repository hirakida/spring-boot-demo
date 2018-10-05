package com.example.actuate;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "custom")
public class CustomEndpoint {

    private final Map<String, String> values = new ConcurrentHashMap<>();

    @ReadOperation
    public Map<String, String> values() {
        return Collections.unmodifiableMap(values);
    }

    @ReadOperation
    public String value(@Selector String key) {
        return values.get(key);
    }

    @WriteOperation
    public void put(@Selector String key, String value) {
        values.put(key, value);
    }

    @DeleteOperation
    public void remove(@Selector String key) {
        values.remove(key);
    }
}
