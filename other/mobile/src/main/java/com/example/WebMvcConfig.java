package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // DeviceResolverAutoConfigurationでやってくれるのでこの設定は不要
//    @Bean
//    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
//        return new DeviceHandlerMethodArgumentResolver();
//    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
//    }
}
