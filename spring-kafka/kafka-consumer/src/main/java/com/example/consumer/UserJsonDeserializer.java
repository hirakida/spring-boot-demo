package com.example.consumer;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.model.User;

public class UserJsonDeserializer extends JsonDeserializer<User> {
}
