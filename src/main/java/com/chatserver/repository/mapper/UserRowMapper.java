package com.chatserver.repository.mapper;

import com.chatserver.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>  {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"), rs.getNString("username"), rs.getNString("password"),
                rs.getBoolean("receive_message"), rs.getTimestamp("date_added"));
    }
}
