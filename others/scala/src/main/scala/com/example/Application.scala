package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class Application

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application])
  }
}
