package com.example.serialization;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.model.User;

public class UserJsonDeserializer extends JsonDeserializer<User> {
}
