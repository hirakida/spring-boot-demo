package com.example;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import org.springframework.boot.actuate.metrics.repository.redis.RedisMetricRepository;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@EnableCaching
@Configuration
public class AppConfig {

    @Bean
    public MetricRepository metricRepository(RedisConnectionFactory redisConnectionFactory) {
        return new RedisMetricRepository(redisConnectionFactory);
    }

    @Bean
    @ExportMetricWriter
    public MetricWriter metricWriter(RedisConnectionFactory redisConnectionFactory,
                                     MetricExportProperties export) {
        return new RedisMetricRepository(redisConnectionFactory,
                                         export.getRedis().getPrefix(),
                                         export.getRedis().getKey());
    }
}
