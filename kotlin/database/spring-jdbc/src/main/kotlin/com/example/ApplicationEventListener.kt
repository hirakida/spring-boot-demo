package com.example

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationEventListener(private val userService: UserService) {
    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() {
        userService.findAll().forEach {
            println(it)
        }

        val user = userService.findById(1)
        println(user)
    }
}
