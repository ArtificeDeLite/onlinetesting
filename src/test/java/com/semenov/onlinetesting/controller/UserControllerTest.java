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

import java.util.List;

import static com.semenov.onlinetesting.controller.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserControllerTest extends AbstractTest {

    private static final String REST_URL = UserController.REST_URL;

    @Autowired
    private MockMvc mvc;

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<User> result = List.of(mapFromJson(content, User[].class));
        List<User> users = List.of(ADMIN, USER_1, USER_2);
        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getId()).isEqualTo(users.get(i).getId());
            assertThat(result.get(i).getLogin()).isEqualTo(users.get(i).getLogin());
        }
    }

    @Test
    void getNotAuth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized())
                .andDo(print()).andReturn();
    }

    @Test
    void getForbidden() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER_1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isForbidden())
                .andDo(print()).andReturn();
    }
}