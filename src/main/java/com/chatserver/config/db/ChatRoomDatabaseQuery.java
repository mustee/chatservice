package com.chatserver.config.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:database.properties")
public class ChatRoomDatabaseQuery {
    @Value("${chat_room.insert}")
    public String INSERT;

    @Value("${chat_room.find_by_name}")
    public String FIND_BY_NAME;

    @Value("${chat_room.find}")
    public String FIND;

    @Value("${chat_room.get_all}")
    public String GET_ALL;

    @Value("${chat_room.find_by_user}")
    public String FIND_BY_USER;

    @Value("${chat_room.delete}")
    public String DELETE;
}
