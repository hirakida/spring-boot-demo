package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public DemoBean demoBean() {
        return new DemoBean();
    }
}
