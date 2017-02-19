package com.example;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AccountService proxyAccountService(MethodInterceptorImpl methodInterceptor,
                                              AccountService accountService) {
        ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        factoryBean.addAdvice(methodInterceptor);
        factoryBean.setTarget(accountService);
        return (AccountService) factoryBean.getObject();
    }
}
