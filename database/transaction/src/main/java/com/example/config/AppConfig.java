package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final PlatformTransactionManager platformTransactionManager;

//    @Bean
//    public DefaultTransactionDefinition defaultTransactionDefinition() {
//        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
//        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        definition.setIsolationLevel(TransactionDefinition.TIMEOUT_DEFAULT);
//        definition.setTimeout(-1);
//        definition.setReadOnly(false);
//        return definition;
//    }

    @Bean
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate template = new TransactionTemplate(platformTransactionManager);
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        template.setIsolationLevel(TransactionDefinition.TIMEOUT_DEFAULT);
        template.setTimeout(-1);
        template.setReadOnly(false);
        return template;
    }
}
