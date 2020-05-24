package com.semenov.onlinetesting.service;

import com.semenov.onlinetesting.OnlinetestingApplication;
import com.semenov.onlinetesting.exception.IllegalRequestDataException;
import com.semenov.onlinetesting.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static com.semenov.onlinetesting.service.QuestionTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Sql(scripts = {"classpath:db/schema.sql", "classpath:db/data.sql"}, config = @SqlConfig(encoding = "UTF-8"))
@EnableConfigurationProperties
@AutoConfigureTestDatabase
@AutoConfigureWebMvc
@SpringBootTest(classes = OnlinetestingApplication.class)
class QuestionServiceTest {

    @Autowired
    QuestionService service;

    @Test
    void getAll() {
        List<Question> all = service.getAll();
        assertThat(all).usingFieldByFieldElementComparator().isEqualTo(List.of(QUESTION_1, QUESTION_2, QUESTION_3, QUESTION_4, QUESTION_5));
    }

    @Test
    void getByNumber() {
        Question question = service.getByNumber(1);
        assertThat(question).isEqualToComparingFieldByField(QUESTION_1);
    }

    @Test
    void getWrongNumber() {
        assertThrows(IllegalRequestDataException.class, () -> service.getByNumber(0));
    }

    @Test
    void addNew() {
        Question question = new Question(null, "Question", "Answer");
        Question created = service.addNew(question);
        question.setId(created.getId());
        assertThat(question).isEqualToComparingFieldByField(created);
    }

    @Test
    void addIllegal() {
        Question question = new Question(null, "", "Answer");
        assertThrows(IllegalRequestDataException.class, () -> service.addNew(question));
    }
}