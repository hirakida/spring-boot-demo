package com.example

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {
    final UserRepository userRepository

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    def findAll() {
        userRepository.findAll()
    }

    def findById(Integer id) {
        userRepository.findById(id).orElseThrow()
    }

    def create(String name) {
        User user = new User(name: name)
        userRepository.save(user)
    }

    def update(Integer id, String name) {
        User user = findById(id)
        user.setName(name)
        userRepository.save(user)
    }

    void delete(Integer id) {
        User user = findById(id)
        userRepository.deleteById(user.id)
    }
}
