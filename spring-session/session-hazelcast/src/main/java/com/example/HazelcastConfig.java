package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@EnableHazelcastHttpSession(maxInactiveIntervalInSeconds = 60)
@Configuration
public class HazelcastConfig {
    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }
}
