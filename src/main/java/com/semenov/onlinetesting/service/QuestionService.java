package com.semenov.onlinetesting.service;

import com.semenov.onlinetesting.exception.NotFoundException;
import com.semenov.onlinetesting.model.Question;
import com.semenov.onlinetesting.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;

    public List<Question> getAll() {
        return repository.findAll();
    }

    public Question getByNumber(int number) {
        List<Question> questions = repository.findAll();
        if (questions.size() < number - 1) {
            throw new NotFoundException("Question does not exist");
        }
        return questions.get(number - 1);
    }

    public Question addNew(Question question) {
        return repository.save(question);
    }

}
