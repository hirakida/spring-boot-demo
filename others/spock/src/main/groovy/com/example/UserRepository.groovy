package com.example

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String name)
}
