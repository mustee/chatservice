package com.chatserver.repository.Impl;

import com.chatserver.models.Message;
import com.chatserver.config.db.MessageDatabaseQuery;
import com.chatserver.models.draft.MessageDraft;
import com.chatserver.repository.MessageRepository;
import com.chatserver.repository.mapper.MessageRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageRepositoryImpl implements MessageRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final MessageDatabaseQuery messageDatabaseQuery;

    public MessageRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, MessageDatabaseQuery messageDatabaseQuery) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.messageDatabaseQuery = messageDatabaseQuery;
    }

    @Override
    public long insert(MessageDraft draft) {
        Map<String, Object> map = new HashMap<>();
        map.put("from_user_id", draft.getFromUserId());
        map.put("to_user_id", draft.getToUserId() == null || !draft.getToUserId().isPresent() ? null : draft.getToUserId().get() );
        map.put("chat_room_id", draft.getChatRoomId() == null || !draft.getChatRoomId().isPresent() ? null : draft.getChatRoomId().get());
        map.put("message", draft.getMessage());
        map.put("timestamp", draft.getTimestamp());

        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(messageDatabaseQuery.INSERT, new MapSqlParameterSource(map), holder);
        return holder.getKey().longValue();
    }

    @Override
    public int delete(long id) {
        return namedParameterJdbcTemplate.update(messageDatabaseQuery.DELETE, new MapSqlParameterSource("id", id));
    }

    @Override
    public List<Message> findByChatRoom(long chatRoomId) {
        return namedParameterJdbcTemplate.query(messageDatabaseQuery.FIND_BY_CHAT_ROOM, new MapSqlParameterSource("chat_room_id", chatRoomId), new MessageRowMapper());
    }

    @Override
    public List<Message> findByToUser(long userId) {
        return namedParameterJdbcTemplate.query(messageDatabaseQuery.FIND_BY_USER, new MapSqlParameterSource("to_user_id", userId), new MessageRowMapper());
    }
}
