package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.To.Statistics;
import com.semenov.onlinetesting.To.UserStatistics;
import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = StatisticController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticController {
    static final String REST_URL = "/statistics";

    @Autowired
    ResultService service;

    @GetMapping("/profile")
    public UserStatistics get(@AuthenticationPrincipal User authUser) {
        return service.getUserStat(authUser.getId());
    }

    @GetMapping
    public Statistics getAllStat() {
        return service.getStat();
    }
}
