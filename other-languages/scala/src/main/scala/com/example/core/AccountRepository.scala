package com.example.core

import java.lang.Iterable

import org.springframework.data.repository.CrudRepository

trait AccountRepository extends CrudRepository[Account, Integer] {

  def findByName(name: String): Iterable[Account]
}
