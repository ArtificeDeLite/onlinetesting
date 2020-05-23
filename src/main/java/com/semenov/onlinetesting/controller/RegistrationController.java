package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.To.UserTo;
import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.repository.JdbcUserRepository;
import com.semenov.onlinetesting.service.UserService;
import com.semenov.onlinetesting.util.IllegalRequestDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = RegistrationController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    static final String REST_URL = "/registration";

    @Autowired
    JdbcUserRepository repository;

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<UserTo> register(@RequestBody User user) {
        User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(QuestionController.REST_URL + "/1").build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(new UserTo(created.getId(),created.getLogin()));
    }
}
