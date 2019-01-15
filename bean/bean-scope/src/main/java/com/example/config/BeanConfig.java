package com.example.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.example.bean.ScopeBean;
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
    public ScopeBean requestBean() {
        return new ScopeBean();
    }

    @Bean
    @SessionScope
    public ScopeBean sessionBean() {
        return new ScopeBean();
    }

    @Bean
    @ApplicationScope
    public ScopeBean applicationBean() {
        return new ScopeBean();
    }

    @Bean
    //@Scope(BeanDefinition.SCOPE_SINGLETON)
    public ScopeBean singletonBean() {
        return new ScopeBean();
    }
}
