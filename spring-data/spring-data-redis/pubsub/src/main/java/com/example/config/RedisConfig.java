package com.example.config;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.example.MessageListenerImpl;

@Configuration
public class RedisConfig {

    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("topic");
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new MessageListenerImpl());
    }

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(RedisConnectionFactory connectionFactory) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter(), channelTopic());
        container.setTaskExecutor(Executors.newFixedThreadPool(1));
        return container;
    }
}
