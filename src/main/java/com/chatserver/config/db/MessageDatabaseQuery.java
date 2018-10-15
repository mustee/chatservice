package com.chatserver.config.db;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:database.properties")
public class MessageDatabaseQuery {

    @Value("${message.insert}")
    public String INSERT;

    @Value("${message.find_by_chat_room}")
    public String FIND_BY_CHAT_ROOM;

    @Value("${message.find_by_user}")
    public String FIND_BY_USER;

    @Value("${message.delete}")
    public String DELETE;

}
