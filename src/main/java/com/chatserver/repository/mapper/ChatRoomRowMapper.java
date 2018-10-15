package com.chatserver.repository.mapper;

import com.chatserver.models.ChatRoom;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatRoomRowMapper implements RowMapper<ChatRoom> {

    @Override
    public ChatRoom mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ChatRoom(rs.getLong("id"), rs.getNString("name"),
                rs.getTimestamp("date_created"));
    }

}
