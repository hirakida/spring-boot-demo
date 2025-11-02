package com.example

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AccountRepository : CoroutineCrudRepository<Account, Int>
