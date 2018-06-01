package com.example;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.actuate.metrics.repository.redis.RedisMetricRepository;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ExportMetricWriter
    public MetricWriter metricWriter(MetricExportProperties export) {
        return new RedisMetricRepository(redisConnectionFactory,
                                         export.getRedis().getPrefix(),
                                         export.getRedis().getKey());
    }
}
