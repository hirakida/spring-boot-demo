package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void findByIdTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("user1");
        user.setEnabled(true);
        LocalDateTime dateTime = LocalDateTime.of(2019, 1, 2, 12, 0);
        user.setCreatedAt(dateTime);
        user.setUpdatedAt(dateTime);
        String expected = objectMapper.writeValueAsString(user);

        mockMvc.perform(get("/users/1"))
               .andExpect(status().isOk())
               .andExpect(content().json(expected));
    }
}
