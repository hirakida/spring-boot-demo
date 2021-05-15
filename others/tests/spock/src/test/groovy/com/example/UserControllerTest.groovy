package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime

import static org.mockito.Mockito.doNothing
import static org.mockito.Mockito.when
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = UserController.class)
class UserControllerTest extends Specification {
    @Autowired
    MockMvc mvc
    @Autowired
    ObjectMapper objectMapper
    @MockBean
    UserService userService

    def "findAll"() {
        setup:
        def createdAt = LocalDateTime.now()
        def updatedAt = createdAt.plusHours(1)
        def users = List.of(user(1, createdAt, updatedAt), user(2, createdAt, updatedAt))
        when(userService.findAll()).thenReturn(users)

        expect:
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [
                        {
                            \"id\":1,
                            \"name\":\"name1\",
                            \"createdAt\":${writeValueAsString(createdAt)},
                            \"updatedAt\":${writeValueAsString(updatedAt)}
                        },
                        {
                            \"id\":2,
                            \"name\":\"name2\",
                            \"createdAt\":${writeValueAsString(createdAt)},
                            \"updatedAt\":${writeValueAsString(updatedAt)}
                        }
                    ]
                """))
    }

    def "findById"() {
        setup:
        def id = 1
        def createdAt = LocalDateTime.now()
        def updatedAt = createdAt.plusHours(1)
        when(userService.findById(id)).thenReturn(user(id, createdAt, updatedAt))

        expect:
        mvc.perform(get("/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        \"id\":${id},
                        \"name\":\"name${id}\",
                        \"createdAt\":${writeValueAsString(createdAt)},
                        \"updatedAt\":${writeValueAsString(updatedAt)}
                    }
                """))
    }

    def "create"() {
        setup:
        def id = 10
        def name = "name_"
        def createdAt = LocalDateTime.now()
        def updatedAt = createdAt.plusHours(1)
        def request = new UserController.UserRequest()
        request.name = name
        when(userService.create(name)).thenReturn(user(id, name, createdAt, updatedAt))

        expect:
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                    {
                        \"id\":${id},
                        \"name\":\"${name}\",
                        \"createdAt\":${writeValueAsString(createdAt)},
                        \"updatedAt\":${writeValueAsString(updatedAt)}
                    }
                """))
    }

    def "update"() {
        setup:
        def id = 10
        def name = "name__"
        def createdAt = LocalDateTime.now()
        def updatedAt = createdAt.plusHours(1)
        def request = new UserController.UserRequest()
        request.name = name
        when(userService.update(id, name)).thenReturn(user(id, name, createdAt, updatedAt))

        expect:
        mvc.perform(put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        \"id\":${id},
                        \"name\":\"${name}\",
                        \"createdAt\":${writeValueAsString(createdAt)},
                        \"updatedAt\":${writeValueAsString(updatedAt)}
                    }
                """))
    }

    def "delete"() {
        setup:
        def id = 10
        doNothing().when(userService).delete(id)

        expect:
        mvc.perform(delete("/users/{id}", id))
                .andExpect(status().isNoContent())
    }

    User user(id, name = "name${id}", createdAt, updatedAt) {
        new User(id: id, name: name, createdAt: createdAt, updatedAt: updatedAt)
    }

    String writeValueAsString(value) {
        objectMapper.writeValueAsString(value)
    }
}
