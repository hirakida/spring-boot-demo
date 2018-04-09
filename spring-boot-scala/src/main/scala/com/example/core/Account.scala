package com.example.core

import java.time.LocalDateTime
import javax.persistence._
import org.springframework.data.annotation.{CreatedDate, LastModifiedDate}
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import scala.annotation.meta.field
import scala.beans.BeanProperty

@Entity
@EntityListeners(Array(classOf[AuditingEntityListener]))
case class Account(@(Id@field)
                   @(GeneratedValue@field)
                   @BeanProperty
                   var id: Int,

                   @BeanProperty
                   @Column(nullable = false)
                   var name: String,

                   @BeanProperty
                   @(CreatedDate@field)
                   @Column(nullable = false, updatable = false)
                   var createdAt: LocalDateTime,

                   @BeanProperty
                   @(LastModifiedDate@field)
                   @Column(nullable = false)
                   var updatedAt: LocalDateTime) {

  def this() = this(0, null, LocalDateTime.MIN, LocalDateTime.MIN)

  def this(name: String) = this(0, name, LocalDateTime.MIN, LocalDateTime.MIN)

  def this(id: Int, name: String) = this(id, name, LocalDateTime.MIN, LocalDateTime.MIN)
}
