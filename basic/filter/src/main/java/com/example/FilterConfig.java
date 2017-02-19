package com.example;

import java.util.Collections;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean1(RequestFilter1 filter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        // 優先度を指定。数値が小さいほうが先に適用される
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2(RequestFilter2 filter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        // filterが適用されるpattern
        bean.setUrlPatterns(Collections.singletonList("/filter/*"));
        return bean;
    }
}
