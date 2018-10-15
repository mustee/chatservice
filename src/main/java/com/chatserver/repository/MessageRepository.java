package com.chatserver.repository;

import com.chatserver.models.Message;
import com.chatserver.models.draft.MessageDraft;

import java.util.List;

public interface MessageRepository {

    long insert(MessageDraft draft);

    int delete(long id);

    List<Message> findByChatRoom(long chatRoomId);

    List<Message> findByToUser(long userId);
}
