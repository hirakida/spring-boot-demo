package com.example

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>
