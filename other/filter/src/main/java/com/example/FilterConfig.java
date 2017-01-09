package com.example;

import java.util.Collections;

import javax.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.example.CustomFilter.RequestFilter1;
import com.example.CustomFilter.RequestFilter2;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FilterConfig {

    @Autowired
    RequestFilter1 requestFilter1;

    @Autowired
    RequestFilter2 requestFilter2;

    @Bean
    public FilterRegistrationBean filterRegistrationBean1() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(requestFilter1);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);  // 優先度を指定。数値が小さいほうが先に適用される
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setUrlPatterns(Collections.singletonList("/filter/*"));    // filterが適用されるpattern
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(requestFilter2);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return registrationBean;
    }
}
