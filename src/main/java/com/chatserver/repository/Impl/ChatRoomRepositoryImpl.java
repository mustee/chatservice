package com.chatserver.repository.Impl;

import com.chatserver.models.ChatRoom;
import com.chatserver.config.db.ChatRoomDatabaseQuery;
import com.chatserver.models.draft.ChatRoomDraft;
import com.chatserver.repository.ChatRoomRepository;
import com.chatserver.repository.mapper.ChatRoomRowMapper;
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
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ChatRoomDatabaseQuery chatRoomDatabaseQuery;

    public ChatRoomRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ChatRoomDatabaseQuery chatRoomDatabaseQuery) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.chatRoomDatabaseQuery = chatRoomDatabaseQuery;
    }

    @Override
    public long insert(ChatRoomDraft draft) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", draft.getName());
        map.put("date_created", draft.getDateCreated());

        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(chatRoomDatabaseQuery.INSERT, new MapSqlParameterSource(map), holder);
        return holder.getKey().longValue();
    }

    @Override
    public Optional<ChatRoom> findByName(String name) {
        List<ChatRoom> chatRooms = namedParameterJdbcTemplate.query(chatRoomDatabaseQuery.FIND_BY_NAME,
                new MapSqlParameterSource("name", name), new ChatRoomRowMapper());
        return chatRooms.size() == 0 ? Optional.empty(): Optional.of(chatRooms.get(0));
    }

    @Override
    public List<ChatRoom> getAll() {
        return namedParameterJdbcTemplate.query(chatRoomDatabaseQuery.GET_ALL, new ChatRoomRowMapper());
    }

    @Override
    public List<ChatRoom> findByUserId(long userId) {
        return namedParameterJdbcTemplate.query(chatRoomDatabaseQuery.FIND_BY_USER,
                new MapSqlParameterSource("user_id", userId), new ChatRoomRowMapper());
    }

    @Override
    public Optional<ChatRoom> get(long id) {
        List<ChatRoom> chatRooms = namedParameterJdbcTemplate.query(chatRoomDatabaseQuery.FIND,
                new MapSqlParameterSource("id", id), new ChatRoomRowMapper());
        return chatRooms.size() == 0 ? Optional.empty(): Optional.of(chatRooms.get(0));
    }


    @Override
    public int delete(long id) {
        return namedParameterJdbcTemplate.update(chatRoomDatabaseQuery.DELETE, new MapSqlParameterSource("id", id));
    }
}
