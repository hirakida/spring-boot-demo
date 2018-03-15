package com.example;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import com.example.AppEndpoint.AppData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
public class AppEndpoint extends AbstractEndpoint<AppData> {

    private static final String ENDPOINT_ID = "app";

    public AppEndpoint() {
        super(ENDPOINT_ID);
    }

    @Override
    public AppData invoke() {
        return new AppData(1, 2, "value3");
    }

    @Data
    @AllArgsConstructor
    public static class AppData {
        private int key1;
        private long key2;
        private String key3;
    }
}
