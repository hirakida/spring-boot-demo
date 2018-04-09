package com.example.web

import scala.beans.BeanProperty

case class ApiAccount(@BeanProperty id: Int,
                      @BeanProperty name: String) {
  def this() = this(0, null)
}
