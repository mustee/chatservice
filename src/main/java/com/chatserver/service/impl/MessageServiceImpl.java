package com.chatserver.service.impl;

import com.chatserver.models.ChatRoom;
import com.chatserver.models.Message;
import com.chatserver.models.User;
import com.chatserver.models.draft.MessageDraft;
import com.chatserver.repository.ChatRoomRepository;
import com.chatserver.repository.MessageRepository;
import com.chatserver.repository.UserRepository;
import com.chatserver.result.ListResult;
import com.chatserver.result.Result;
import com.chatserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private UserRepository userRepository;
    private ChatRoomRepository chatRoomRepository;
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(UserRepository userRepository, ChatRoomRepository chatRoomRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Result sendMessageToUser(long fromUserId, long toUserId, String message) {
        Optional<User> fromUser = userRepository.get(toUserId);
        if(!fromUser.isPresent()){
            return Result.failed("User does not exist");
        }

        Optional<User> toUser = userRepository.get(toUserId);
        if(!toUser.isPresent()){
            return Result.failed("User does not exist");
        }

        messageRepository.insert(new MessageDraft().draft().fromUserId(fromUserId)
                .toUserId(Optional.of(toUserId)).message(message).timestamp(new Date()).build());
        return Result.succeeded();
    }

    @Override
    public Result sendMessageToChatRoom(long fromUserId, long chatRoomId, String message) {
        Optional<User> fromUser = userRepository.get(fromUserId);
        if(!fromUser.isPresent()){
            return Result.failed("User does not exist");
        }

        Optional<ChatRoom> chatRoom = chatRoomRepository.get(chatRoomId);
        if(!chatRoom.isPresent()){
            return Result.failed("Chat room does not exist");
        }

        messageRepository.insert(new MessageDraft().draft().fromUserId(fromUserId)
                .chatRoomId(Optional.of(chatRoomId)).message(message).timestamp(new Date()).build());
        return Result.succeeded();
    }

    @Override
    public Result listChatRoomMessages(long chatRoomId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.get(chatRoomId);
        if(!chatRoom.isPresent()){
            return Result.failed("Chat room does not exist");
        }

        List<Message> messages = messageRepository.findByChatRoom(chatRoomId);
        return new ListResult<>(messages);
    }

    @Override
    public Result listUserMessages(long userId) {
        Optional<User> fromUser = userRepository.get(userId);
        if(!fromUser.isPresent()){
            return Result.failed("User does not exist");
        }

        List<Message> messages = messageRepository.findByToUser(userId);
        return new ListResult<>(messages);
    }
}
