package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.model.Question;
import com.semenov.onlinetesting.model.Result;
import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.repository.JdbcQuestionRepository;
import com.semenov.onlinetesting.repository.JdbcResultRepository;
import com.semenov.onlinetesting.util.IllegalRequestDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = QuestionController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    static final String REST_URL = "/testing";

    public static final int NUMBER_OF_QUESTIONS = 5;

    @Autowired
    JdbcQuestionRepository repository;

    @Autowired
    JdbcResultRepository resultRepository;

    @GetMapping("/{number}")
    public Question get(@PathVariable int number) {
        numberCheck(number);
        List<Question> questions = repository.findAll();
        return questions.get(number);
    }

    @GetMapping
    public List<Question> getAll() {
        return repository.findAll();
    }

    @PostMapping(value = "/{number}")
    public Result answer(@PathVariable int number, @RequestBody String answer, @AuthenticationPrincipal User authUser) {
        numberCheck(number);
        List<Question> questions = repository.findAll();
        Question currentQuestion = questions.get(number);

        List<Result> results = resultRepository.findAllByUserId(authUser.getId());

        for (Result result : results) {
            if (result.getQuestionId() == currentQuestion.getId()) {
                return result;
            }
        }

        boolean check = false;
        if (questions.get(number).getAnswer().toLowerCase().equals(answer.toLowerCase())) {
            check = true;
        }
        return resultRepository.save(new Result(null, authUser.getId(), questions.get(number).getId(), answer, check));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Question add(@RequestBody Question question) {
        if (question.getQuestion() == null) {
            throw new IllegalRequestDataException("question must not be null");
        }
        if (question.getAnswer() == null) {
            throw new IllegalRequestDataException("answer must not be null");
        }

        return repository.save(question);
    }

    private void numberCheck(int number) {
        if (number <= 0 || number > NUMBER_OF_QUESTIONS) {
            throw new IllegalRequestDataException("wrong number of question");
        }
    }
}
