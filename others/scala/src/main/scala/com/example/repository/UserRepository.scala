package com.example.repository

import com.example.entity.User
import org.springframework.data.repository.CrudRepository

trait UserRepository extends CrudRepository[User, Integer] {

  def findByName(name: String): java.lang.Iterable[User]
}
