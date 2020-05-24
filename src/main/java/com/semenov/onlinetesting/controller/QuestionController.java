package com.semenov.onlinetesting.controller;

import com.semenov.onlinetesting.model.Question;
import com.semenov.onlinetesting.model.Result;
import com.semenov.onlinetesting.model.User;
import com.semenov.onlinetesting.repository.JdbcQuestionRepository;
import com.semenov.onlinetesting.repository.JdbcResultRepository;
import com.semenov.onlinetesting.service.QuestionService;
import com.semenov.onlinetesting.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.semenov.onlinetesting.util.ValidationUtil.notBlankCheck;
import static com.semenov.onlinetesting.util.ValidationUtil.numberCheck;

@RestController
@RequestMapping(value = QuestionController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    public static final String REST_URL = "/testing";

    public static final int NUMBER_OF_QUESTIONS = 5;

    @Autowired
    JdbcQuestionRepository repository;

    @Autowired
    JdbcResultRepository resultRepository;

    @Autowired
    QuestionService service;

    @Autowired
    ResultService resultService;

    @GetMapping("/{number}")
    public Question get(@PathVariable int number) {
        numberCheck(number);
        return service.getByNumber(number);
    }

    @GetMapping
    public List<Question> getAll() {
        return service.getAll();
    }

    @PostMapping(value = "/{number}")
    public Result answer(@PathVariable int number, @RequestBody String answer, @AuthenticationPrincipal User authUser) {
        numberCheck(number);
        notBlankCheck(answer, "Answer");
        List<Question> questions = service.getAll();
        Question currentQuestion = questions.get(number);

        return resultService.resisterAnswer(currentQuestion, answer, authUser.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Question add(@RequestBody Question question) {

        notBlankCheck(question.getQuestion(), "Question");
        notBlankCheck(question.getAnswer(), "Answer");
        return service.addNew(question);
    }
}
