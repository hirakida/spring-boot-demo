package com.example;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.example.bean.MyBean;
import com.example.bean.PrototypeBean;

@Configuration
public class BeanConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean
    @RequestScope
    public MyBean requestBean() {
        return new MyBean();
    }

    @Bean
    @SessionScope
    public MyBean sessionBean() {
        return new MyBean();
    }

    @Bean
    @ApplicationScope
    public MyBean applicationBean() {
        return new MyBean();
    }

    @Bean
    //@Scope(BeanDefinition.SCOPE_SINGLETON)
    public MyBean singletonBean() {
        return new MyBean();
    }
}
