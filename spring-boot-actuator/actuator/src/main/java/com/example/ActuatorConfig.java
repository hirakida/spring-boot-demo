package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActuatorConfig {
    @Bean
    public MapInfoContributor mapInfoContributor() {
        Map<String, Object> details = Map.of("time", LocalDateTime.now(),
                                             "version", SpringBootVersion.getVersion());
        return new MapInfoContributor(Map.of("contributor", details));
    }
}
