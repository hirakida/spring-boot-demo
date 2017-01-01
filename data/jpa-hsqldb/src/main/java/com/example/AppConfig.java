package com.example;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AppConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public AuditingEntityListener createAuditingListener() {
        return new AuditingEntityListener();
    }
}
