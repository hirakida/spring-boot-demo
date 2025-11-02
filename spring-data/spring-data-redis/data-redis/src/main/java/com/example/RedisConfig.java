package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory connectionFactory,
                                                         ObjectMapper objectMapper) {
        final StringRedisSerializer keySerializer = new StringRedisSerializer();
        final Jackson2JsonRedisSerializer<User> valueSerializer =
                new Jackson2JsonRedisSerializer<>(objectMapper, User.class);

        final RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        return redisTemplate;
    }
}
