package com.example

import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UserController.class])
class UserControllerTest extends Specification {
    @Autowired
    MockMvc mvc
    @Autowired
    ObjectMapper objectMapper
    @SpringBean
    UserRepository userRepository = Mock()

    def "findAll"() {
        given:
        def users = List.of(new User(id: 1, name: "name1"), new User(id: 2, name: "name2"))
        userRepository.findAll() >> users

        expect:
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [
                        {
                            \"id\":1,
                            \"name\":\"name1\"
                        },
                        {
                            \"id\":2,
                            \"name\":\"name2\"
                        }
                    ]
                """))
    }

    def "findById"() {
        given:
        def id = 1
        userRepository.findById(id) >> Optional.of(new User(id: id, name: "name$id"))

        expect:
        mvc.perform(get("/users/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        \"id\":$id,
                        \"name\":\"name$id\"
                    }
                """))
    }

    def "create"() {
        given:
        def id = 10
        def name = "name10"
        def request = new UserController.UserRequest()
        request.name = name
        userRepository.save(_ as User) >> new User(id: id, name: name)

        expect:
        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                    {
                        \"id\":$id,
                        \"name\":\"$name\"
                    }
                """))
    }

    def "update"() {
        given:
        def id = 10
        def name = "updated"
        def request = new UserController.UserRequest()
        request.name = name
        userRepository.findById(id) >> Optional.of(new User(id: id, name: "name10"))
        userRepository.save(_ as User) >> new User(id: id, name: name)

        expect:
        mvc.perform(put("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        \"id\":$id,
                        \"name\":\"updated\"
                    }
                """))
    }

    def "delete"() {
        setup:
        def id = 10

        expect:
        mvc.perform(delete("/users/{id}", id))
                .andExpect(status().isNoContent())
    }

    private String writeValueAsString(value) {
        objectMapper.writeValueAsString(value)
    }
}
