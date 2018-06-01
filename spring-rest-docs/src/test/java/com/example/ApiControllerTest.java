package com.example;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.core.Account;
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
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/accounts"))
               .andExpect(status().isOk());
    }

    @Test
    public void findOneTest() throws Exception {
        List<Account> accounts = getAccounts();
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/accounts/{id}",
                                                             accounts.get(0).getId()))
               .andExpect(status().isOk())
               .andDo(document("ApiController",
                               pathParameters(parameterWithName("id").description("account id"))));
    }

    @Test
    public void updateTest() throws Exception {
        List<Account> accounts = getAccounts();
        Account account = accounts.get(0);
        account.setName("updated Account");
        account.setName("put@example.com");
        mockMvc.perform(RestDocumentationRequestBuilders.put("/api/accounts/{id}",
                                                             account.getId())
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(account)))
               .andExpect(status().isOk())
               .andDo(document("ApiController",
                               pathParameters(parameterWithName("id").description("account id"))));
    }

    @Test
    public void createTest() throws Exception {
        Account account = new Account();
        account.setName("new Account");
        account.setEmail("post@example.com");
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/accounts",
                                                             account.getId())
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(objectMapper.writeValueAsString(account)))
               .andExpect(status().isOk())
               .andDo(document("ApiController"));
    }

    @Test
    public void deleteTest() throws Exception {
        List<Account> accounts = getAccounts();
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/accounts/{id}",
                                                                accounts.get(0).getId()))
               .andExpect(status().isOk())
               .andDo(document("ApiController",
                               pathParameters(parameterWithName("id").description("account id"))));
    }

    private List<Account> getAccounts() throws Exception {
        String url = "http://localhost:" + port + "/api/accounts";
        Account[] accounts = testRestTemplate.getForObject(url, Account[].class);
        return Arrays.asList(accounts);
    }
}
