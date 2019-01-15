package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.bean.DemoBean;
import com.example.bean.LifecycleBean;

@Configuration
public class BeanConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy2")
    @Primary
    public DemoBean demoBean() {
        DemoBean bean = new DemoBean();
        bean.setName("DemoBean");
        return bean;
    }

    @Bean
    public LifecycleBean lifecycleBean() {
        LifecycleBean bean = new LifecycleBean();
        bean.setName("LifecycleBean");
        return bean;
    }
}
