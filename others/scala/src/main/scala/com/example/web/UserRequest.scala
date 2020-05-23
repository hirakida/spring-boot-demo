package com.example.web

import javax.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Range

import scala.annotation.meta.field
import scala.beans.BeanProperty

case class UserRequest(@BeanProperty
                       @(NotEmpty@field)
                       name: String,

                       @BeanProperty
                       @(Range@field)(min = 20, max = 60)
                       age: Byte) {

  def this() = this(null, 0)
}
