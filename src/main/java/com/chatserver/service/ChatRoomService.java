package com.chatserver.service;

import com.chatserver.result.Result;

public interface ChatRoomService {

    Result createChatRoom(String name);

    Result listChatRooms();

    Result validateUser(String chatRoom, String username);
}
