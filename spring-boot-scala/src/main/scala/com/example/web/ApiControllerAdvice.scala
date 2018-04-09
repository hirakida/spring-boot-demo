package com.example.web

import com.example.core.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import scala.beans.BeanProperty

@ControllerAdvice
class ApiControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Array(classOf[DataNotFoundException]))
  def handleResourceAccessException(e: DataNotFoundException, request: WebRequest): ResponseEntity[Object] = {
    val response = ErrorResponse(HttpStatus.NOT_FOUND.value, HttpStatus.NOT_FOUND.getReasonPhrase)
    handleExceptionInternal(e, response, null, HttpStatus.OK, request)
  }
}

case class ErrorResponse(@BeanProperty errorCode: Int,
                         @BeanProperty errorMessage: String)
