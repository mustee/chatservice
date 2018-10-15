package com.chatserver.models.draft;

public class SubscribeDraft {
    private long userId;
    private long chatRoomId;

    public SubscribeDraft(){}

    protected SubscribeDraft(long userId, long chatRoomId) {
        this.userId = userId;
        this.chatRoomId = chatRoomId;
    }

    public long getUserId() {
        return userId;
    }

    public long getChatRoomId() {
        return chatRoomId;
    }

    public Builder draft()
    {
        return new Builder(this);
    }


    public class Builder {
        private long userId;
        private long chatRoomId;


        Builder(SubscribeDraft draft){
            userId = draft.userId;
            chatRoomId = draft.chatRoomId;
        }


        public Builder userId(long userId){
            this.userId = userId;
            return this;
        }

        public Builder chatRoomId(long chatRoomId){
            this.chatRoomId = chatRoomId;
            return this;
        }

        public SubscribeDraft build(){
            return new SubscribeDraft(userId, chatRoomId);
        }
    }
}
