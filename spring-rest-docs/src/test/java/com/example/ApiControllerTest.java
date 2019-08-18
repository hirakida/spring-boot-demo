package com.example;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.ApiController.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
public class ApiControllerTest {
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                 .apply(documentationConfiguration(restDocumentation))
                                 .alwaysDo(document("{method-name}/{step}/"))
                                 .build();
    }

    @Test
    public void findAllTest() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/users"))
               .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        List<User> users = getUsers();
        mockMvc.perform(RestDocumentationRequestBuilders.get("/users/{id}",
                                                             users.get(0).getId()))
               .andExpect(status().isOk())
               .andDo(document("ApiController",
                               pathParameters(parameterWithName("id").description("user id"))));
    }

    @Test
    public void updateTest() throws Exception {
        List<User> users = getUsers();
        User user = users.get(0);
        UserRequest request = new UserRequest();
        request.setName("updated User");
        request.setEmail("put@example.com");
        mockMvc.perform(RestDocumentationRequestBuilders.put("/users/{id}", user.getId())
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andDo(document("ApiController",
                               pathParameters(parameterWithName("id").description("user id"))));
    }

    @Test
    public void createTest() throws Exception {
        UserRequest request = new UserRequest();
        request.setName("new User");
        request.setEmail("post@example.com");
        mockMvc.perform(RestDocumentationRequestBuilders.post("/users")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andDo(document("ApiController"));
    }

    @Test
    public void deleteTest() throws Exception {
        List<User> users = getUsers();
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/users/{id}",
                                                                users.get(0).getId()))
               .andExpect(status().isNoContent())
               .andDo(document("ApiController",
                               pathParameters(parameterWithName("id").description("user id"))));
    }

    private List<User> getUsers() {
        String url = "http://localhost:" + port + "/users";
        User[] users = testRestTemplate.getForObject(url, User[].class);
        return List.of(users);
    }
}
