package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.glassdoor.planout4j.NamespaceFactory;
import com.glassdoor.planout4j.spring.Planout4jAppContext;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Import(Planout4jAppContext.class)
@Slf4j
public class Planout4jConfig {

    public Planout4jConfig(NamespaceFactory namespaceFactory) {
        log.info("namespaceCount: {}", namespaceFactory.getNamespaceCount());
    }
}
