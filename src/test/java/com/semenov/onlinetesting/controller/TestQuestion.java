package com.semenov.onlinetesting.controller;

public class TestQuestion {

    private String question;
    private String answer;

    public TestQuestion() {
    }

    public TestQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
