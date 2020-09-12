package com.example;

import java.net.InetSocketAddress;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    @Value("${elasticsearch.host:localhost}")
    private String host;
    @Value("${elasticsearch.port:9200}")
    private int port;

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        InetSocketAddress endpoint = new InetSocketAddress(host, port);
        ClientConfiguration configuration = ClientConfiguration.builder()
                                                               .connectedTo(endpoint)
                                                               .build();
        return RestClients.create(configuration).rest();
    }
}
