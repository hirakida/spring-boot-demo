package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
@SuppressWarnings("serial")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private LocalDateTime createdAt;
}
