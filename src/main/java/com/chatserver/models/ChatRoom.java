package com.chatserver.models;

import com.chatserver.models.draft.ChatRoomDraft;

import java.util.Date;

public class ChatRoom extends ChatRoomDraft {
    private long id;

    public ChatRoom(long id, String name, Date dateCreated) {
        super(name, dateCreated);
        this.id = id;
    }


    public long getId() {
        return id;
    }
}
