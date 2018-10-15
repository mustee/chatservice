package com.chatserver.models;

import com.chatserver.models.draft.SubscribeDraft;

public class Subscribe extends SubscribeDraft {
    private long id;

    public Subscribe(long id, long userId, long chatRoomId) {
        super(userId, chatRoomId);
        this.id = id;
    }


    public long getId() {
        return id;
    }
}
