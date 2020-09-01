package com.example;

import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InfoContributorImpl implements InfoContributor {
    private final Environment environment;

    @Override
    public void contribute(Builder builder) {
        Map<String, Object> details = Map.of("environment",
                                             Map.of("activeProfiles", environment.getActiveProfiles(),
                                                    "defaultProfiles", environment.getDefaultProfiles()));
        builder.withDetails(details);
    }
}
