package com.example

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener.class)
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column(nullable = false)
    String name

    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt

    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime updatedAt
}
