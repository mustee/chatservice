package com.chatserver.models.draft;

import java.util.Date;

public class UserDraft {
    private String username;
    private String password;
    private boolean receiveMessage;
    private Date dateAdded;

    public UserDraft(){}

    protected UserDraft(String username, String password, boolean receiveMessage, Date dateAdded) {
        this.username = username;
        this.password = password;
        this.receiveMessage = receiveMessage;
        this.dateAdded = dateAdded;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean shouldReceiveMessage() {
        return receiveMessage;
    }

    public Date getDateAdded() {
        return dateAdded;
    }


    public Builder draft()
    {
        return new Builder(this);
    }


    public class Builder {
        private String username;
        private String password;
        private Date dateAdded;
        private boolean receiveMessage;


        Builder(UserDraft draft){
            username = draft.username;
            password = draft.password;
            receiveMessage = draft.receiveMessage;
            dateAdded = draft.dateAdded;
        }

        public Builder username(String userName){
            this.username = userName;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder dateAdded(Date dateAdded){
            this.dateAdded = dateAdded;
            return this;
        }

        public Builder recieveMessage(boolean receiveMessage) {
            this.receiveMessage = receiveMessage;
            return this;
        }

        public UserDraft build(){
            return new UserDraft(username, password, receiveMessage, dateAdded);
        }
    }
}
