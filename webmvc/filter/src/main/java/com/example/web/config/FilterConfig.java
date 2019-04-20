package com.example.web.config;

import java.util.List;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

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
    public FilterRegistrationBean<OncePerRequestFilterExt> requestFilterBean1() {
        FilterRegistrationBean<OncePerRequestFilterExt> bean =
                new FilterRegistrationBean<>(new OncePerRequestFilterExt());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        bean.setUrlPatterns(List.of("/v1/*"));
        return bean;
    }

    @Bean
    public FilterRegistrationBean<OncePerRequestFilterExt> requestFilterBean2() {
        FilterRegistrationBean<OncePerRequestFilterExt> bean =
                new FilterRegistrationBean<>(new OncePerRequestFilterExt());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        bean.setUrlPatterns(List.of("/v2/*"));
        return bean;
    }

    @Bean
    public RequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }

    @Bean
    public ServletRequestListenerImpl servletRequestListener() {
        return new ServletRequestListenerImpl();
    }
}
