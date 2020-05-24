package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.service.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.semenov.onlinetesting.controller.UserTestData.USER_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class RegistrationControllerTest extends AbstractTest {

    private static final String REST_URL = RegistrationController.REST_URL;

    @Autowired
    private MockMvc mvc;

    @Test
    void register() throws Exception {
        TestUser newUser = new TestUser("login", "password");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(newUser)))
                .andDo(print()).andExpect(status().isCreated())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        User result = (mapFromJson(content, User.class));
        assertThat(result.getLogin()).isEqualTo(newUser.getLogin());

    }

    @Test
    void registerAuth() throws Exception {
        TestUser newUser = new TestUser("login", "password");

        mvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(USER_1))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(newUser)))
                .andDo(print()).andExpect(status().isForbidden())
                .andReturn();

    }
}