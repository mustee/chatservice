package com.chatserver.controllers.models;

import javax.validation.constraints.NotNull;

public class SubscribeModel {

    @NotNull
    private long userId;


    public long getUserId() {
        return userId;
    }
}
