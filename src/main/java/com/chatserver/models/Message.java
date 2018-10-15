package com.chatserver.models;

import com.chatserver.models.draft.MessageDraft;

import java.util.Date;
import java.util.Optional;

public class Message extends MessageDraft {
    private long id;

    public Message(long id, long fromUserId, Optional<Long> toUserId, Optional<Long> chatRoomId, String message, Date timestamp) {
        super(fromUserId, toUserId, chatRoomId, message, timestamp);
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
