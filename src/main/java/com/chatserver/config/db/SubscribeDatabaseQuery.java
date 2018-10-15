package com.chatserver.config.db;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:database.properties")
public class SubscribeDatabaseQuery {

    @Value("${subscribe.insert}")
    public String INSERT;

    @Value("${subscribe.delete}")
    public String DELETE;
}
