package com.example.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
