package com.example.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
class Account {
    @Id
    @GeneratedValue
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
