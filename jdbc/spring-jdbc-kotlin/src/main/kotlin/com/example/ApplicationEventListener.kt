package com.example

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationEventListener(private val userRepository: UserRepository) {
    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() {
        userRepository.findAll().forEach {
            println(it)
        }
    }
}
