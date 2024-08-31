package com.example

import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationEventListener(private val userRepository: UserRepository) {
    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() = runBlocking {
        val users = (1..5)
            .map { (User(null, "name$it")) }
            .toList()
        userRepository.saveAll(users)
    }
}
