package com.semenov.onlinetesting.To;

public class UserTo {

    private int id;
    private String login;

    public UserTo() {
    }

    public UserTo(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
