package com.semenov.onlinetesting.repository;

import com.semenov.onlinetesting.model.User;

import java.util.List;

public interface UserRepository {
    int count();

    User save(User user);

    User update(User user);

    int deleteById(int id);

    User get(int id);

    User getByLogin(String login);

    List<User> findAll();

}
