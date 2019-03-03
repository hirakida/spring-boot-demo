package com.example.config;

import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableElasticsearchRepositories
@Configuration
public class ElasticsearchConfig {
    private static final String CLUSTER_NAME = "docker-cluster";
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 9300;

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client, ElasticsearchConverter converter,
                                                       CustomEntityMapper customEntityMapper) {
        return new ElasticsearchTemplate(client, converter, customEntityMapper);
    }

    @Bean
    public Client client() {
        Settings settings = Settings.builder()
                                    .put("cluster.name", CLUSTER_NAME)
                                    .build();
        TransportClient client = new PreBuiltTransportClient(settings);
        try {
            client.addTransportAddress(new TransportAddress(InetAddress.getByName(ADDRESS), PORT));
        } catch (UnknownHostException e) {
            throw new UncheckedIOException(e);
        }
        return client;
    }

    @Bean
    public CustomEntityMapper customEntityMapper(ObjectMapper objectMapper) {
        return new CustomEntityMapper(objectMapper);
    }
}
