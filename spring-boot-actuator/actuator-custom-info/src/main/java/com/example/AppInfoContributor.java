package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
public class AppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Builder builder) {
        builder.withDetail("app1", new Detail(new Random().nextInt(),
                                              new Random().nextInt()));
        Map<String, Object> map = new HashMap<>();
        map.put("app2", new Detail(new Random().nextInt(), new Random().nextInt()));
        map.put("app3", new Detail(new Random().nextInt(), new Random().nextInt()));
        builder.withDetails(map);
    }

    @Data
    @AllArgsConstructor
    private static class Detail {
        private int key1;
        private int key2;
    }
}
