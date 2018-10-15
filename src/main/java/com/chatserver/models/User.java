package com.chatserver.models;


import com.chatserver.models.draft.UserDraft;

import java.util.Date;

public class User extends UserDraft {
    private long id;

    public User(long id, String username, String password, boolean receiveMessage, Date dateAdded) {
        super(username, password, receiveMessage, dateAdded);
        this.id = id;
    }


    public long getId() {
        return id;
    }

}
