package com.example.entity

import java.time.LocalDateTime

import javax.persistence._
import org.springframework.data.annotation.{CreatedDate, LastModifiedDate}
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import scala.annotation.meta.field
import scala.beans.BeanProperty

@Entity
@EntityListeners(Array(classOf[AuditingEntityListener]))
case class User(@BeanProperty
                @(Id@field)
                @(GeneratedValue@field)(strategy = GenerationType.IDENTITY)
                var id: Int,

                @BeanProperty
                @(Column@field)(nullable = false)
                var name: String,

                @BeanProperty
                @(Column@field)(nullable = false)
                var age: Byte,

                @BeanProperty
                @(CreatedDate@field)
                @(Column@field)(nullable = false, updatable = false)
                var createdAt: LocalDateTime,

                @BeanProperty
                @(LastModifiedDate@field)
                @(Column@field)(nullable = false)
                var updatedAt: LocalDateTime) {

  def this() = this(0, null, 0, LocalDateTime.MIN, LocalDateTime.MIN)

  def this(name: String, age: Byte) = this(0, name, age, LocalDateTime.MIN, LocalDateTime.MIN)

  def this(id: Int, name: String, age: Byte) = this(id, name, age, LocalDateTime.MIN, LocalDateTime.MIN)
}
