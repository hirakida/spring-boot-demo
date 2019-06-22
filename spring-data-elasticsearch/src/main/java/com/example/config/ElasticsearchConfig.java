package com.example.config;

import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableElasticsearchRepositories
@Configuration
public class ElasticsearchConfig {

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client, ElasticsearchConverter converter,
                                                       CustomEntityMapper customEntityMapper) {
        return new ElasticsearchTemplate(client, converter, customEntityMapper);
    }

    @Bean
    public CustomEntityMapper customEntityMapper(ObjectMapper objectMapper) {
        return new CustomEntityMapper(objectMapper);
    }
}
