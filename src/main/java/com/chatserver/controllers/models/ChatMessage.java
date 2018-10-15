package com.chatserver.controllers.models;

import javax.validation.constraints.NotNull;

public class ChatMessage {

    @NotNull
    private String message;

    private String username;

    public String getMessage() {
        return message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
