package com.chatserver.controllers.models;

import javax.validation.constraints.NotNull;

public class CreateChatRoomModel {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }
}
