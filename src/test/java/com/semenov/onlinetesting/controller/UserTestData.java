package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.model.Role;
import com.semenov.onlinetesting.model.User;

public class UserTestData {

    private static final int START_SEQ = 100;

    public static final int USER_1_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int USER_2_ID = START_SEQ + 2;

    public static final User USER_1 = new User(USER_1_ID, "User", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin", Role.ADMIN);
    public static final User USER_2 = new User(USER_2_ID, "testUser", "test", Role.USER);

}
