package com.semenov.onlinetesting.To;

public class Statistics {

    public static final int RIGHT_ANSWERS_TO_PASS = 4;

    private int userCount;
    private int userPassed;
    private int userAllQuestionCount;
    private int getUserAllQuestionRightCount;

    public Statistics() {
    }

    public Statistics(int userCount, int userPassed, int userAllQuestionCount, int getUserAllQuestionRightCount) {
        this.userCount = userCount;
        this.userPassed = userPassed;
        this.userAllQuestionCount = userAllQuestionCount;
        this.getUserAllQuestionRightCount = getUserAllQuestionRightCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getUserPassed() {
        return userPassed;
    }

    public void setUserPassed(int userPassed) {
        this.userPassed = userPassed;
    }

    public int getUserAllQuestionCount() {
        return userAllQuestionCount;
    }

    public void setUserAllQuestionCount(int userAllQuestionCount) {
        this.userAllQuestionCount = userAllQuestionCount;
    }

    public int getGetUserAllQuestionRightCount() {
        return getUserAllQuestionRightCount;
    }

    public void setGetUserAllQuestionRightCount(int getUserAllQuestionRightCount) {
        this.getUserAllQuestionRightCount = getUserAllQuestionRightCount;
    }
}
