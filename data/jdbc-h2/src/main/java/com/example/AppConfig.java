package com.example;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Autowired
//    DataSourceProperties dataSourceProperties;

    // spring bootではDataSourceはAutoConfigureで自動設定される
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

    // 個別に設定したい場合はDataSourcePropertiesを使う
//    DataSource dataSource() {
//        return DataSourceBuilder.create(dataSourceProperties.getClassLoader())
//                                .url(dataSourceProperties.getUrl())
//                                .username(dataSourceProperties.getUsername())
//                                .password(dataSourceProperties.getPassword())
//                                .build();
//    }
}
