package com.semenov.onlinetesting.repository;

import com.semenov.onlinetesting.model.Question;

import java.util.List;

public interface QuestionRepository {

    Question save(Question question);

    Question update(Question question);

    int deleteById(int id);

    List<Question> findAll();

    Question get(int id);
}
