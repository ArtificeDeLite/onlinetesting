package com.semenov.onlinetesting.service;

import com.semenov.onlinetesting.To.ResultTo;
import com.semenov.onlinetesting.To.Statistics;
import com.semenov.onlinetesting.To.UserStatistics;
import com.semenov.onlinetesting.controller.QuestionController;
import com.semenov.onlinetesting.model.Question;
import com.semenov.onlinetesting.model.Result;
import com.semenov.onlinetesting.repository.JdbcQuestionRepository;
import com.semenov.onlinetesting.repository.JdbcResultRepository;
import com.semenov.onlinetesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    JdbcResultRepository repository;

    @Autowired
    JdbcQuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    public List<ResultTo> get(int userId) {
        List<Result> results = repository.findAllByUserId(userId);
        List<Question> questions = questionRepository.findAll();

        Map<Integer, Question> map = questions.stream().collect(Collectors.toMap(Question::getId, q -> q));

        List<ResultTo> resultsTo = results.stream()
                .map(r -> new ResultTo(r.getId(), map.get(r.getQuestionId()), r.getUserAnswer(), r.isResult()))
                .collect(Collectors.toList());
        return resultsTo;
    }

    public List<Result> getAllByUserId(int userId) {
        return repository.findAllByUserId(userId);
    }

    public UserStatistics getUserStat(int userId) {
        UserStatistics statistics = new UserStatistics();

        int passed = repository.countPassedById(userId);
        int above = repository.countAbove(passed);
        int below = repository.countBelow(passed);
        int userCount = userRepository.count();

        if (userCount == 0) {
            userCount = 1;
        }

        statistics.setPassingPercent(passed * 100 / QuestionController.NUMBER_OF_QUESTIONS);
        statistics.setAbovePercent(above * 100 / userCount);
        statistics.setBelowPercent(below * 100 / userCount);
        return statistics;
    }

    public Statistics getStat() {
        Statistics statistics = new Statistics();

        int userCount = userRepository.count();
        int passedCount = repository.countPassed(Statistics.RIGHT_ANSWERS_TO_PASS);
        int answerAllQuestionsCount = repository.countQuestions(QuestionController.NUMBER_OF_QUESTIONS);
        int answerAllRightCount = repository.countPassed(QuestionController.NUMBER_OF_QUESTIONS);

        statistics.setUserCount(userCount);
        statistics.setUserPassed(passedCount);
        statistics.setUserAllQuestionCount(answerAllQuestionsCount);
        statistics.setGetUserAllQuestionRightCount(answerAllRightCount);

        return statistics;

    }
}
