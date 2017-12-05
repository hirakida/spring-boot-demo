package com.example;

import java.util.Collections;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.example.filter.RequestFilter1;
import com.example.filter.RequestFilter2;

@Configuration
public class FilterConfig {

    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(1500);
        return filter;
    }

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
        bean.setUrlPatterns(Collections.singletonList("/2/*"));
        return bean;
    }
}
