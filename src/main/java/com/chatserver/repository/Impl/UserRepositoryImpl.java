package com.chatserver.repository.Impl;

import com.chatserver.models.User;
import com.chatserver.config.db.UserDatabaseQuery;
import com.chatserver.models.draft.UserDraft;
import com.chatserver.repository.UserRepository;
import com.chatserver.repository.mapper.UserRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserDatabaseQuery userDatabaseQuery;

    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, UserDatabaseQuery userDatabaseQuery) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userDatabaseQuery = userDatabaseQuery;
    }

    @Override
    public long insert(UserDraft draft) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", draft.getUsername());
        map.put("password", draft.getPassword());
        map.put("receive_message", draft.shouldReceiveMessage());
        map.put("date_added", draft.getDateAdded());

        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(userDatabaseQuery.INSERT, new MapSqlParameterSource(map), holder);
        return holder.getKey().longValue();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = namedParameterJdbcTemplate.query(userDatabaseQuery.FIND_BY_USERNAME,
                new MapSqlParameterSource("username", username), new UserRowMapper());
        return users.size() == 0 ? Optional.empty(): Optional.of(users.get(0));
    }

    @Override
    public Optional<User> get(long id) {
        List<User> users = namedParameterJdbcTemplate.query(userDatabaseQuery.FIND,
                new MapSqlParameterSource("id", id), new UserRowMapper());
        return users.size() == 0 ? Optional.empty(): Optional.of(users.get(0));
    }

    @Override
    public int update(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        map.put("receive_message", user.shouldReceiveMessage());
        map.put("date_added", user.getDateAdded());
        map.put("id", user.getId());

        return namedParameterJdbcTemplate.update(userDatabaseQuery.UPDATE, new MapSqlParameterSource(map));
    }

    @Override
    public int delete(long id) {
        return namedParameterJdbcTemplate.update(userDatabaseQuery.DELETE, new MapSqlParameterSource("id", id));
    }
}
