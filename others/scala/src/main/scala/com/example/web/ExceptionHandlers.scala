package com.example.web

import javax.persistence.EntityNotFoundException
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{ExceptionHandler, RestControllerAdvice}

@RestControllerAdvice
class ExceptionHandlers {

  @ExceptionHandler(Array(classOf[EntityNotFoundException]))
  def handleResponseStatusException(): ResponseEntity[AnyRef] = {
    new ResponseEntity(HttpStatus.NOT_FOUND)
  }
}
