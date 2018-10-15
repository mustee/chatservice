package com.chatserver.controllers.models;

import javax.validation.constraints.NotNull;

public class CreateUserModel {

    @NotNull
    private String username;

    @NotNull
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
