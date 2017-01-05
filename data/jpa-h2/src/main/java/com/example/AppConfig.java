package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AppConfig {

    // spring bootではDataSourceはAutoConfigureで自動設定される
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }

    // spring bootではこの定義はいらないみたい
//    @Bean
//    public AuditingEntityListener createAuditingListener() {
//        return new AuditingEntityListener();
//    }
}
