package com.example.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.domain.AccountService;

@Configuration
public class AppConfig {

    @Bean
    public AccountService proxyAccountService(AccountService accountService) {
        ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        factoryBean.addAdvice(methodInterceptor());
        factoryBean.setTarget(accountService);
        return (AccountService) factoryBean.getObject();
    }

    @Bean
    public MethodInterceptorImpl methodInterceptor() {
        return new MethodInterceptorImpl();
    }
}
