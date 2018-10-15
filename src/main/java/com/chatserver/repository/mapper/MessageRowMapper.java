package com.chatserver.repository.mapper;

import com.chatserver.models.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessageRowMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Message(rs.getLong("id"), rs.getLong("from_user_id"),
                rs.getObject("to_user_id") == null ? Optional.empty() : Optional.of(rs.getLong("to_user_id")),
                rs.getObject("chat_room_id") == null ? Optional.empty() : Optional.of(rs.getLong("chat_room_id")),
                rs.getNString("message"), rs.getTimestamp("timestamp"));
    }
}
