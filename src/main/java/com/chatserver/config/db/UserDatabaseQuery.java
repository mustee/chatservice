package com.chatserver.config.db;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:database.properties")
public class UserDatabaseQuery {

    @Value("${user.insert}")
    public String INSERT;

    @Value("${user.find}")
    public String FIND;

    @Value("${user.find_by_username}")
    public String FIND_BY_USERNAME;

    @Value("${user.update}")
    public String UPDATE;

    @Value("${user.delete}")
    public String DELETE;
}
