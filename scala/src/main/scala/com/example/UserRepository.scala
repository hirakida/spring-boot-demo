package com.example

import org.springframework.data.repository.CrudRepository

trait UserRepository extends CrudRepository[User, Integer] {
}
