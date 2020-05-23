package com.semenov.onlinetesting.repository;

import com.semenov.onlinetesting.To.ResultTo;
import com.semenov.onlinetesting.controller.QuestionController;
import com.semenov.onlinetesting.model.Result;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcResultRepository implements ResultRepository {

    private static final BeanPropertyRowMapper<Result> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Result.class);
    private static final BeanPropertyRowMapper<ResultTo> ROW_MAPPER_TO = BeanPropertyRowMapper.newInstance(ResultTo.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertResult;

    public JdbcResultRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertResult = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("results")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Result save(Result result) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(result);

        Number newKey = insertResult.executeAndReturnKey(parameterSource);
        result.setId(newKey.intValue());

        return result;
    }

    @Override
    public Result update(Result result) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(result);

        if (namedParameterJdbcTemplate.update(
                "UPDATE results SET userId=:userId, questionId=:questionId, userAnswer=:userAnswer, resul=:result WHERE id=:id", parameterSource) == 0) {
            return null;
        }

        return result;
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM results WHERE id=?", id);
    }

    @Override
    public List<Result> findAll() {
        return jdbcTemplate.query("SELECT * FROM results", ROW_MAPPER);
    }

    @Override
    public List<Result> findAllByUserId(int userId) {
        return jdbcTemplate.query("SELECT * FROM results WHERE user_id=?", ROW_MAPPER, userId);
        //return jdbcTemplate.query("SELECT * FROM results LEFT JOIN questions ON results.question_id = questions.id WHERE user_id=?", ROW_MAPPER, userId);
    }

/*
    public List<ResultTo> findAllByUserId2(int userId) {
        //return jdbcTemplate.query("SELECT * FROM results WHERE user_id=?", ROW_MAPPER, userId);
        return jdbcTemplate.query("SELECT * FROM results LEFT JOIN questions ON results.question_id = questions.id WHERE user_id=?", ROW_MAPPER_TO, userId);
    }*/

    @Override
    public Result get(int id) {
        List<Result> results = jdbcTemplate.query("SELECT * FROM results WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(results);
    }

    @Override
    public int countPassedById(int userId) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM results WHERE user_id=? AND result=true", Integer.class, userId);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int countBelow(int rightAnswersCount) {
        //Integer count = jdbcTemplate.queryForObject("SELECT COUNT(user_id) FROM (SELECT user_id, COUNT(user_id) AS rightAnswers FROM results WHERE result=true GROUP BY user_id) as uir WHERE rightAnswers < ?", Integer.class, rightAnswersCount);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(user_id) FROM (SELECT user_id, COUNT(user_id) AS rightAnswers FROM results WHERE result=true GROUP BY user_id) as uir WHERE rightAnswers < ?", Integer.class, rightAnswersCount);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int countAbove(int rightAnswersCount) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(user_id) FROM (SELECT user_id, COUNT(user_id) AS rightAnswers FROM results WHERE result=true GROUP BY user_id) as uir WHERE rightAnswers > ?", Integer.class, rightAnswersCount);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int countPassed(int rightAnswersToPass) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(user_id) FROM (SELECT user_id, COUNT(user_id) AS rightAnswers FROM results WHERE result=true GROUP BY user_id) as uir WHERE rightAnswers >= ?", Integer.class, rightAnswersToPass);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public int countQuestions(int numberOfQuestions) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(user_id) FROM (SELECT user_id, COUNT(user_id) AS rightAnswers FROM results GROUP BY user_id) as uir WHERE rightAnswers >= ?", Integer.class, numberOfQuestions);
        if (count != null) {
            return count;
        }
        return 0;
    }



}
