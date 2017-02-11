package com.example;

import java.util.Collections;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.example.filter.RequestFilter1;
import com.example.filter.RequestFilter2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class FilterConfig {

    final RequestFilter1 requestFilter1;

    final RequestFilter2 requestFilter2;

    @Bean
    public FilterRegistrationBean filterRegistrationBean1() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(requestFilter1);
        // 優先度を指定。数値が小さいほうが先に適用される
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        // filterが適用されるpattern
        registrationBean.setUrlPatterns(Collections.singletonList("/filter/*"));
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
