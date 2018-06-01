package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {

    //    @Bean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getValueSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory connectionFactory,
                                                         ObjectMapper objectMapper) {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
        serializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(redisTemplate.getKeySerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getValueSerializer());
        return redisTemplate;
    }
}
