package com.semenov.onlinetesting.repository;

import com.semenov.onlinetesting.model.Question;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcQuestionRepository implements QuestionRepository {


    private static final BeanPropertyRowMapper<Question> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Question.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertQuestion;

    public JdbcQuestionRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertQuestion = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("questions")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public Question save(Question question) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(question);

        Number newKey = insertQuestion.executeAndReturnKey(parameterSource);
        question.setId(newKey.intValue());
        return question;
    }

    @Override
    public Question update(Question question) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(question);

        if (namedParameterJdbcTemplate.update(
                "UPDATE questions SET question=:question, answer=:answer WHERE id=:id", parameterSource) == 0) {
            return null;
        }

        return question;
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM questions WHERE id=?", id);
    }

    @Override
    public List<Question> findAll() {
        return jdbcTemplate.query("SELECT * FROM questions", ROW_MAPPER);
    }

    @Override
    public Question get(int id) {
        List<Question> questions = jdbcTemplate.query("SELECT * FROM questions WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(questions);
    }
}
