package com.semenov.onlinetesting.repository;

import com.semenov.onlinetesting.model.Role;
import com.semenov.onlinetesting.model.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Repository
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int count() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        if (count != null) {
            return count;
        }
        return 0;
    }

    @Override
    public User save(User user) {

        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        Number newKey = insertUser.executeAndReturnKey(parameterSource);
        user.setId(newKey.intValue());
        insertRoles(user);

        return user;
    }

    @Override
    public User update(User user) {

        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (namedParameterJdbcTemplate.update(
                "UPDATE users SET name=:name, password=:password WHERE id=:id", parameterSource) == 0) {
            return null;
        }
        deleteRoles(user);
        insertRoles(user);
        return user;
    }

    @Override
    public int deleteById(int id) {
        // != 0 - good situation
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByLogin(String login) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE login=?", ROW_MAPPER, login);
        User user = DataAccessUtils.singleResult(users);
        if (user != null) {
            List<Role> roles = jdbcTemplate.queryForList("SELECT role FROM user_roles WHERE user_id=?", Role.class, user.getId());
            user.setRoles(roles);
        }
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY login", ROW_MAPPER);
    }


    private void insertRoles(User user) {
        Set<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            jdbcTemplate.batchUpdate("INSERT INTO user_roles (user_id, role) VALUES (?, ?)", roles, roles.size(),
                    (ps, role) -> {
                        ps.setInt(1, user.getId());
                        ps.setString(2, role.name());
                    });
        }
    }

    private void deleteRoles(User u) {
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", u.getId());
    }
}
