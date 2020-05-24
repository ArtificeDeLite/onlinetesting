package com.semenov.onlinetesting.controller;

public class TestUser {
    String login;
    String password;

    public TestUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public TestUser() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
