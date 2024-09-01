package com.example.serialization;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.User;

public class UserJsonDeserializer extends JsonDeserializer<User> {
}
