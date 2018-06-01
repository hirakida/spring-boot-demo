package com.example.config;

import java.util.Collections;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.example.filter.OncePerRequestFilter1;
import com.example.filter.OncePerRequestFilter2;

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
    public FilterRegistrationBean<OncePerRequestFilter1> requestFilterBean(OncePerRequestFilter1 filter) {
        FilterRegistrationBean<OncePerRequestFilter1> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter2> v2RequestFilerBean(OncePerRequestFilter2 filter) {
        FilterRegistrationBean<OncePerRequestFilter2> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        bean.setUrlPatterns(Collections.singletonList("/v2/*"));
        return bean;
    }
}
