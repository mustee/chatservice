package com.chatserver.models.draft;

import java.util.Date;
import java.util.Optional;

public class MessageDraft {
    private long fromUserId;
    private Optional<Long> toUserId;
    private Optional<Long> chatRoomId;
    private String message;
    private Date timestamp;

    public MessageDraft(){}

    protected MessageDraft(long fromUserId, Optional<Long> toUserId, Optional<Long> chatRoomId, String message, Date timestamp) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public Optional<Long> getToUserId() {
        return toUserId;
    }

    public Optional<Long> getChatRoomId() {
        return chatRoomId;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    public Builder draft()
    {
        return new Builder(this);
    }


    public class Builder {
        private long fromUserId;
        private Optional<Long> toUserId;
        private Optional<Long> chatRoomId;
        private String message;
        private Date timestamp;

        Builder(MessageDraft draft){
            fromUserId = draft.fromUserId;
            toUserId = draft.toUserId;
            chatRoomId = draft.chatRoomId;
            message = draft.message;
            timestamp = draft.timestamp;
        }


        public Builder fromUserId(long fromUserId){
            this.fromUserId = fromUserId;
            return this;
        }

        public Builder toUserId(Optional<Long> toUserId){
            this.toUserId = toUserId;
            return this;
        }

        public Builder chatRoomId(Optional<Long> chatRoomId){
            this.chatRoomId = chatRoomId;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder timestamp(Date timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public MessageDraft build(){
            return new MessageDraft(fromUserId, toUserId, chatRoomId, message, timestamp);
        }
    }
}
