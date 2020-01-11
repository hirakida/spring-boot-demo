package com.example.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(SnakeCaseStrategy.class)
@Data
public class User {
    private long id;
    private String login;
    private String url;
    private String type;
    private String name;
    private String company;
    private String location;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
