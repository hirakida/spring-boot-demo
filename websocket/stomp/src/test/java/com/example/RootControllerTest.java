package com.example;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StompApplication.class,  webEnvironment = WebEnvironment.RANDOM_PORT)
//@DirtiesContext
public class RootControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @MockBean
    private SimpMessagingTemplate simpMessagingTemplate;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void sendMessageTest() throws Exception {
        String message = "hello!";
        doNothing().when(simpMessagingTemplate).convertAndSend("/api/room/1", message);
        mockMvc.perform(get("/api/room/1").param("message", message))
               .andExpect(status().isOk());
    }
}
