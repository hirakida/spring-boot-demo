package com.example;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.UserController.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider contextProvider) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(documentationConfiguration(contextProvider))
                                 .alwaysDo(document("{method-name}/{step}/"))
                                 .build();
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/users"))
               .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {
        List<User> users = getUsers();
        mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{id}",
                                                             users.get(0).getId()))
               .andExpect(status().isOk())
               .andDo(document("UserController",
                               pathParameters(parameterWithName("id").description("user id"))));
    }

    @Test
    public void update() throws Exception {
        List<User> users = getUsers();
        User user = users.get(0);
        UserRequest request = new UserRequest();
        request.setName("updated User");
        request.setEmail("put@example.com");
        mockMvc.perform(RestDocumentationRequestBuilders.put("/users/{id}", user.getId())
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andDo(document("UserController",
                               pathParameters(parameterWithName("id").description("user id"))));
    }

    @Test
    public void create() throws Exception {
        UserRequest request = new UserRequest();
        request.setName("new User");
        request.setEmail("post@example.com");
        mockMvc.perform(RestDocumentationRequestBuilders.post("/users")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andDo(document("UserController"));
    }

    @Test
    public void deleteById() throws Exception {
        List<User> users = getUsers();
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/users/{id}",
                                                                users.get(0).getId()))
               .andExpect(status().isNoContent())
               .andDo(document("UserController",
                               pathParameters(parameterWithName("id").description("user id"))));
    }

    private List<User> getUsers() {
        String url = UriComponentsBuilder.newInstance()
                                         .scheme("http")
                                         .host("localhost")
                                         .port(port)
                                         .path("/users")
                                         .toUriString();
        User[] users = testRestTemplate.getForObject(url, User[].class);
        return List.of(users);
    }
}
