package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.To.ResultTo;
import com.semenov.onlinetesting.model.Result;
import com.semenov.onlinetesting.service.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.semenov.onlinetesting.controller.UserTestData.USER_1;
import static com.semenov.onlinetesting.service.ResultTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ResultControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getTo() throws Exception {
        String uri = ResultController.REST_URL;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(userHttpBasic(USER_1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        List<ResultTo> result = List.of(mapFromJson(content, ResultTo[].class));
        List<Result> expect = List.of(RESULT_1, RESULT_2, RESULT_3, RESULT_4, RESULT_5);
        assertThat(result).usingElementComparatorIgnoringFields("question_id", "question").isEqualTo(expect);

        for (int i = 0; i < result.size(); i++) {
            assertThat(result.get(i).getQuestion().getId()).isEqualTo(expect.get(i).getQuestionId());
        }
    }
}