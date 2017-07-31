package com.example;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(listener = AccountListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(updatable = false)
    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
