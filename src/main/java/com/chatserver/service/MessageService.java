package com.chatserver.service;

import com.chatserver.result.Result;

public interface MessageService {

    Result sendMessageToUser(long fromUserId, long toUserId, String message);

    Result sendMessageToChatRoom(long fromUserId, long chatRoomId, String message);

    Result listChatRoomMessages(long chatRoomId);

    Result listUserMessages(long userId);

}
