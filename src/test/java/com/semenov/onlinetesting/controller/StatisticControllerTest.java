package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.To.Statistics;
import com.semenov.onlinetesting.To.UserStatistics;
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
class StatisticControllerTest extends AbstractTest {

    private static final String REST_URL = StatisticController.REST_URL;

    @Autowired
    private MockMvc mvc;

    @Test
    void get() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER_1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Statistics result = (mapFromJson(content, Statistics.class));
        Statistics statistics = new Statistics(3, 2, 3, 1);
        assertThat(result).isEqualToComparingFieldByField(statistics);
    }

    @Test
    void getAllStat() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(REST_URL + "/profile")
                .with(userHttpBasic(USER_1))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        UserStatistics result = (mapFromJson(content, UserStatistics.class));
        UserStatistics statistics = new UserStatistics(80, 33, 33);
        assertThat(result).isEqualToComparingFieldByField(statistics);
    }
}