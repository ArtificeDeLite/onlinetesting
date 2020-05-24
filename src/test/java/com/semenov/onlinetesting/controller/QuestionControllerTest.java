package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.model.Question;
import com.semenov.onlinetesting.model.Result;
import com.semenov.onlinetesting.model.Role;
import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.service.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static com.semenov.onlinetesting.controller.UserTestData.*;
import static com.semenov.onlinetesting.service.QuestionTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class QuestionControllerTest extends AbstractTest {

    private static final String REST_URL = QuestionController.REST_URL;

    @Autowired
    private MockMvc mvc;

    @Test
    void getNumber() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(REST_URL + "/1")
                .with(userHttpBasic(USER_1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Question result = (mapFromJson(content, Question.class));
        assertThat(result).isEqualToIgnoringGivenFields(QUESTION_1, "answer");
    }

    @Test
    void getWrongNumber() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(REST_URL + "/8")
                .with(userHttpBasic(USER_1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print()).andReturn();
    }

    @Test
    void getUnAuth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(REST_URL + "/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized())
                .andDo(print()).andReturn();
    }

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER_1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Question> result = List.of(mapFromJson(content, Question[].class));
        assertThat(result).usingElementComparatorIgnoringFields("answer").isEqualTo(List.of(QUESTION_1, QUESTION_2, QUESTION_3, QUESTION_4, QUESTION_5));
    }

    @Test
    void answer() throws Exception {
        TestUser newUser = new TestUser("login", "password");

        MvcResult userResult = mvc.perform(MockMvcRequestBuilders.post(RegistrationController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(newUser)))
                .andExpect(status().isCreated())
                .andReturn();

        User user = (mapFromJson(userResult.getResponse().getContentAsString(), User.class));
        user.setPassword("password");
        user.setRoles(Collections.singleton(Role.USER));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(REST_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(userHttpBasic(user))
                .content(("Answer")))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Result result = (mapFromJson(content, Result.class));
        assertThat(result).isEqualToComparingFieldByField(new Result(result.getId(), user.getId(), result.getQuestionId(), "Answer", false));
    }

    @Test
    void add() throws Exception {
        Question question = new Question(null, "question", "answer");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(userHttpBasic(ADMIN))
                .content(mapToJson(new TestQuestion("question", "answer"))))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Question result = (mapFromJson(content, Question.class));
        question.setId(result.getId());
        assertThat(result).isEqualToIgnoringGivenFields(question, "answer");
    }
}