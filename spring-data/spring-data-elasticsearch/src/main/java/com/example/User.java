package com.example;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "user")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String message;
    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private LocalDateTime createdAt;
}
