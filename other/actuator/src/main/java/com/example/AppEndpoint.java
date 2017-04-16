package com.example;

import java.util.Map;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import com.example.AppEndpoint.AppData;
import com.google.common.collect.ImmutableMap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
public class AppEndpoint extends AbstractEndpoint<AppData> {

    public AppEndpoint() {
        super("app");
    }

    @Override
    public AppData invoke() {
        Map<String, Integer> app = ImmutableMap.of("key1", 1,
                                                   "key2", 2,
                                                   "key3", 3,
                                                   "key4", 4,
                                                   "key5", 5);
        return new AppData(app);
    }

    @Data
    @AllArgsConstructor
    public static class AppData {
        private Map<String, Integer> app;
    }
}
