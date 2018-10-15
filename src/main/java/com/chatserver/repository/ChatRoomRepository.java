package com.chatserver.repository;

import com.chatserver.models.ChatRoom;
import com.chatserver.models.draft.ChatRoomDraft;

import java.util.List;
import java.util.Optional;


public interface ChatRoomRepository {

    long insert(ChatRoomDraft draft);

    Optional<ChatRoom> get(long id);

    int delete(long id);

    Optional<ChatRoom> findByName(String name);

    List<ChatRoom> getAll();

    List<ChatRoom> findByUserId(long userId);
}
