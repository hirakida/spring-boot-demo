package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.bean.DemoBean;
import com.example.bean.LifecycleBean;

@Configuration
public class BeanConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public DemoBean demoBean() {
        DemoBean bean = new DemoBean();
        bean.setName("demoBean");
        return bean;
    }

    @Bean
    public LifecycleBean lifecycleBean() {
        LifecycleBean bean = new LifecycleBean();
        bean.setName("lifecycleBean");
        return bean;
    }
}
