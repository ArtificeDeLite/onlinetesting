package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.To.UserTo;
import com.semenov.onlinetesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    static final String REST_URL = "/users";

    @Autowired
    UserService service;

    @GetMapping
    public List<UserTo> getAll() {
        return service.getAll().stream()
                .map(u -> new UserTo(u.getId(), u.getLogin()))
                .collect(Collectors.toList());
    }
}
