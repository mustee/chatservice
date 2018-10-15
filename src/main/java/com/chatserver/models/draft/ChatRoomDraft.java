package com.chatserver.models.draft;

import java.util.Date;

public class ChatRoomDraft {
    private String name;
    private Date dateCreated;

    public ChatRoomDraft(){}

    protected ChatRoomDraft(String name, Date dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
    }


    public String getName() {
        return name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Builder draft()
    {
        return new Builder(this);
    }


    public class Builder {
        private String name;
        private Date dateCreated;


        Builder(ChatRoomDraft draft){
            name = draft.name;
            dateCreated = draft.dateCreated;
        }


        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder dateCreated(Date dateCreated){
            this.dateCreated = dateCreated;
            return this;
        }

        public ChatRoomDraft build(){
            return new ChatRoomDraft(name, dateCreated);
        }
    }

}
