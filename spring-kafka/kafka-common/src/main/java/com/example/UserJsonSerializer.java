package com.example;

import org.springframework.kafka.support.serializer.JsonSerializer;

public class UserJsonSerializer extends JsonSerializer<User> {
}
