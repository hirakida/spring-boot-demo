package com.example.config;

import java.util.List;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;

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

//    @Bean
    public ServletContextRequestLoggingFilter servletContextRequestLoggingFilter() {
        return new ServletContextRequestLoggingFilter();
    }

    @Bean
    public FilterRegistrationBean<CustomRequestLoggingFilter> requestLoggingFilter1() {
        FilterRegistrationBean<CustomRequestLoggingFilter> registrationBean =
                new FilterRegistrationBean<>(new CustomRequestLoggingFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.setUrlPatterns(List.of("/v1/*"));
        return registrationBean;
    }

    @Bean
    public ServletRequestListenerImpl servletRequestListener() {
        return new ServletRequestListenerImpl();
    }
}
