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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class IntegrationTest {


    private static String CORRECT_USER_NAME = "pdomanskiendava";
    private static String CORRECT_URL = "/repository/" + CORRECT_USER_NAME + "/";
    private static String INCORRECT_USERNAME_URL = "/repository/asdjhsdcvhkhbdfakbdadjhb/";
    private static String MISSING_USERNAME_URL = "/repository/";
    private static String EMPTY_RESULT = "/repository/pdomanski204/";
    private static String EMPTY_ARRAY = "[]";


    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Integration test which calls correctly endpoint with existing user")
    public void happy_path() throws Exception {
        mvc.perform(get(CORRECT_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(CORRECT_USER_NAME)));
    }

    @Test
    @DisplayName("Integration test which calls correctly endpoint with existing user")
    public void not_existing_user() throws Exception {
        mvc.perform(get(INCORRECT_USERNAME_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
    @Test
    @DisplayName("Integration test which calls /repository/ endpoint without name")
    public void missing_username() throws Exception {
        mvc.perform(get(MISSING_USERNAME_URL).contentType(MediaType.APPLICATION_JSON))
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
    @Test
    @DisplayName("Integration test which calls endpoint correctly but there is no repos or branches")
    public void lack_of_result() throws Exception {
        mvc.perform(get(EMPTY_RESULT).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(EMPTY_ARRAY));
    }
}
