package com.semenov.onlinetesting.model;

public class Result {

    private Integer id;
    private int userId;
    private int questionId;
    private String userAnswer;
    private boolean result;

    public Result(Integer id, int userId, int questionId, String userAnswer, boolean result) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
        this.result = result;
    }

    public Result() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", userId=" + userId +
                ", questionId=" + questionId +
                ", userAnswer='" + userAnswer + '\'' +
                ", result=" + result +
                '}';
    }
}
