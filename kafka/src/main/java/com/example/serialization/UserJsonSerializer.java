package com.example.serialization;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.User;

public class UserJsonSerializer extends JsonSerializer<User> {
}
