package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class InfoContributorImpl implements InfoContributor {
    @Override
    public void contribute(Builder builder) {
        Map<String, Object> details = Map.of("time", LocalDateTime.now());
        builder.withDetails(details);
    }
}
