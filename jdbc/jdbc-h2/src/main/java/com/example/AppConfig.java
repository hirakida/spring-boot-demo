package com.example;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Autowired
//    DataSourceProperties dataSourceProperties;

    @Bean
    @ConfigurationProperties("spring.datasource")
    DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    // 個別に設定したい場合はDataSourcePropertiesを使う
//    DataSource dataSource() {
//        return DataSourceBuilder.create(dataSourceProperties.getClassLoader())
//                                .url(dataSourceProperties.getUrl())
//                                .username(dataSourceProperties.getUsername())
//                                .password(dataSourceProperties.getPassword())
//                                .build();
//    }
}
