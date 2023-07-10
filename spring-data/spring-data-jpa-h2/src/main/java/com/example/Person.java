package com.example;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Slf4j
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private boolean enabled;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        log.info("PrePersist: {}", this);
    }

    @PreUpdate
    public void preUpdate() {
        log.info("PreUpdate: {}", this);
    }

    @PostPersist
    public void postPersist() {
        log.info("postPersist: {}", this);
    }

    @PostUpdate
    public void postUpdate() {
        log.info("postUpdate: {}", this);
    }
}
