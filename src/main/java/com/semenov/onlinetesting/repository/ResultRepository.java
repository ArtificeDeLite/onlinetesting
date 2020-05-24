package com.semenov.onlinetesting.repository;

import com.semenov.onlinetesting.model.Result;

import java.util.List;

public interface ResultRepository {

    Result save(Result result);

    Result update(Result result);

    int deleteById(int id);

    List<Result> findAll();

    List<Result> findAllByUserId(int userId);

    Result get(int id);

    int countPassed(int rightAnswersToPass);

    int countPassedById(int userId);

    int countAbove(int rightAnswersCount);

    int countBelow(int rightAnswersCount);

    int countQuestions(int numberOfQuestions);
}
