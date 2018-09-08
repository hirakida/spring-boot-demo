package com.example.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//@Configuration
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
