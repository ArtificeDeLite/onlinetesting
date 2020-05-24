package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.To.ResultTo;
import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ResultController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ResultController {
    static final String REST_URL = "/testing/result";

    @Autowired
    ResultService service;

    @GetMapping
    public List<ResultTo> getTo(@AuthenticationPrincipal User authUser) {
        return service.get(authUser.getId());
    }

}
