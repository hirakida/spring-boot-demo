package com.example;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@SuppressWarnings("serial")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @CreatedDate
    @Column
    private LocalDateTime createdAt;
}
