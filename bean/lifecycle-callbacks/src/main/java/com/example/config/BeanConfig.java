package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import com.example.bean.BeanImpl;
import com.example.bean.LifecycleImpl;
import com.example.bean.POJOBean;

@Configuration
public class BeanConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Primary
    public POJOBean pojoBean1() {
        POJOBean bean = new POJOBean();
        bean.setName("POJO1");
        bean.setMessage("@Bean + @Primary");
        return bean;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Lazy
    @LazyBean
    public POJOBean pojoBean2() {
        POJOBean bean = new POJOBean();
        bean.setName("POJO2");
        bean.setMessage("@Bean + @Lazy");
        return bean;
    }

    @Bean
    public BeanImpl beanImpl() {
        BeanImpl bean = new BeanImpl();
        bean.setName("BeanImpl");
        bean.setMessage("@Bean");
        return bean;
    }

    @Bean
    public LifecycleImpl lifecycleImpl() {
        LifecycleImpl bean = new LifecycleImpl();
        bean.setName("LifecycleImpl");
        bean.setMessage("@Bean");
        return bean;
    }
}
