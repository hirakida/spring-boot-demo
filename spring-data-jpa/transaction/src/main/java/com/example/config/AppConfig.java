package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class AppConfig {

    final PlatformTransactionManager platformTransactionManager;

    @Bean
    public DefaultTransactionDefinition defaultTransactionDefinition() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        // 以下はdefault値なので設定しなくても変わらない
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        definition.setIsolationLevel(TransactionDefinition.TIMEOUT_DEFAULT);
        definition.setTimeout(-1);
        definition.setReadOnly(false);
        return definition;
    }

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
