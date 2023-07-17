package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String[] INCLUDE_PATTERNS = { "/**" };
    private static final String[] EXCLUDE_PATTERNS = { "/v2/**", "/error" };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorImpl())
                .addPathPatterns(INCLUDE_PATTERNS)
                .excludePathPatterns(EXCLUDE_PATTERNS);
    }

    //    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(INCLUDE_PATTERNS, EXCLUDE_PATTERNS, new HandlerInterceptorImpl());
    }

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
    public FilterRegistrationBean<CustomRequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<CustomRequestLoggingFilter> registrationBean =
                new FilterRegistrationBean<>(new CustomRequestLoggingFilter());
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        registrationBean.addUrlPatterns("/v1/*");
        return registrationBean;
    }

    @Bean
    public ServletRequestListenerImpl servletRequestListener() {
        return new ServletRequestListenerImpl();
    }
}
