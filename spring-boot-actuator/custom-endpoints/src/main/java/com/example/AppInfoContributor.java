package com.example;

import java.util.Map;
import java.util.Random;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

@Component
public class AppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Builder builder) {
        Map<String, Object> details = ImmutableMap.of("app",
                                                      ImmutableMap.of("detail",
                                                                      new Random().nextInt()));
        builder.withDetails(details).build();
    }
}
