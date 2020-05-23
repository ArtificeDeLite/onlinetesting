package com.semenov.onlinetesting.To;

import com.semenov.onlinetesting.model.Question;

public class ResultTo {

    private Integer id;
    private Question question;
    private String userAnswer;
    private boolean result;


    public ResultTo(Integer id, Question question, String userAnswer, boolean result) {
        this.id = id;
        this.question = question;
        this.userAnswer = userAnswer;
        this.result = result;
    }

    public ResultTo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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
}
