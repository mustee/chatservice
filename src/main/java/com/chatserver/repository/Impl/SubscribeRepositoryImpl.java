package com.chatserver.repository.Impl;

import com.chatserver.config.db.SubscribeDatabaseQuery;
import com.chatserver.models.draft.SubscribeDraft;
import com.chatserver.repository.SubscribeRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SubscribeRepositoryImpl implements SubscribeRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SubscribeDatabaseQuery subscribeDatabaseQuery;

    public SubscribeRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, SubscribeDatabaseQuery subscribeDatabaseQuery) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.subscribeDatabaseQuery = subscribeDatabaseQuery;
    }


    @Override
    public int delete(long id) {
        return namedParameterJdbcTemplate.update(subscribeDatabaseQuery.DELETE, new MapSqlParameterSource("id", id));
    }

    @Override
    public long insert(SubscribeDraft draft) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", draft.getUserId());
        map.put("chat_room_id", draft.getChatRoomId());

        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(subscribeDatabaseQuery.INSERT, new MapSqlParameterSource(map), holder);
        return holder.getKey().longValue();
    }
}
