package com.example.support;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.model.User;

public class UserJsonSerializer extends JsonSerializer<User> {
}
