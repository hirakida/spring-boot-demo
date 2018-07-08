package com.hirakida.spring.boot;

import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ManagementContextConfiguration
public class HelloAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "endpoints.hello")
    public HelloNamedMvcEndpoint helloNamedMvcEndpoint() {
        return new HelloNamedMvcEndpoint();
    }
}
