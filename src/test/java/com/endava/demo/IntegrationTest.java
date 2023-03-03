package com.endava.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {


    private String CORRECT_URL = "/repository/pdomanskiendava/";
    private String INCORRECT_USERNAME_URL = "/repository/asdjhsdcvhkhbdfakbdadjhb/";

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Integration test which calls correctly endpoint with existing user")
    public void happy_path() throws Exception {
        mvc.perform(get(CORRECT_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("Integration test which calls correctly endpoint with existing user")
    public void not_existing_user() throws Exception {
        mvc.perform(get(INCORRECT_USERNAME_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("Integration test which calls endpoint with header Accept: application/xml")
    public void incorrect_accept_header() throws Exception {
        mvc.perform(get(CORRECT_URL).accept(MediaType.APPLICATION_XML).contentType(MediaType.APPLICATION_XML))
                .andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
}
