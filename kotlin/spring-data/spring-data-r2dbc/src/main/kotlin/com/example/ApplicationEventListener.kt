package com.example

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.reactive.TransactionalOperator
import reactor.core.publisher.Mono

@Component
class ApplicationEventListener(
    private val operator: TransactionalOperator,
    private val userRepository: UserRepository
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() {
        val user1: Mono<User> =
            userRepository.findById(1)
                .map { user -> User(user.id, user.name + "!") }
                .flatMap { userRepository.save(it) }
        operator.transactional(user1)
            .subscribe { log.info("$it") }
    }
}
