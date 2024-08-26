package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @MockBean
    lateinit var userRepository: UserRepository

    @Test
    fun findAll() {
        val list = listOf(
            User(1, "name1", 20, LocalDateTime.now(), LocalDateTime.now()),
            User(2, "name2", 30, LocalDateTime.now(), LocalDateTime.now())
        )
        val response = objectMapper.writeValueAsString(list)
        `when`(userRepository.findAll()).thenReturn(list)

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk)
            .andExpect(content().json(response))
    }
}
