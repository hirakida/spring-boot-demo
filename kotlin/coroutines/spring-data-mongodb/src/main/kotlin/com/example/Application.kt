package com.example

import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication
class Application(private val userRepository: UserRepository) {

    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() = runBlocking {
        (1..5).map { userRepository.insert(User(null, "name$it")) }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
