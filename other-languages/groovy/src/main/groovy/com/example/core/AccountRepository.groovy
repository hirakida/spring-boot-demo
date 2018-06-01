package com.example.core

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByName(String name)
}
