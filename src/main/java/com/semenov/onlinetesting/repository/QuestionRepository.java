package com.semenov.onlinetesting.repository;

import com.semenov.onlinetesting.model.Question;
import com.semenov.onlinetesting.model.Result;

import java.util.List;

public interface QuestionRepository {

    Question save(Question question);

    Question update(Question question);

    int deleteById (int id);

    List<Question> findAll();

    Question get(int id);

    //List<Question> findAllByUserId(int userId);


}
