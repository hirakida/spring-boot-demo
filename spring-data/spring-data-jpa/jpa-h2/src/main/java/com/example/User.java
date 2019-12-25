package com.example;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Slf4j
public class User {
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
