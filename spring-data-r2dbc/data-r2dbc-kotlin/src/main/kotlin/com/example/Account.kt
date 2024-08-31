package com.example

import org.springframework.data.annotation.Id

data class Account(
    @field:Id val id: Int,
    val name: String
)
