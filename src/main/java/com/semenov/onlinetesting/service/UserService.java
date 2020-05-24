package com.semenov.onlinetesting.service;

import com.semenov.onlinetesting.model.Role;
import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.repository.UserRepository;
import com.semenov.onlinetesting.exception.IllegalRequestDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = repository.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " is not found");
        }
        return user;
    }

    public User create(User user) {
        User userExist = repository.getByLogin(user.getLogin());
        if (userExist != null) {
            throw new IllegalRequestDataException("login already exists");
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);

        return user;
    }
}
