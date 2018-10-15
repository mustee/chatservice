package com.chatserver.service.impl;

import com.chatserver.models.ChatRoom;
import com.chatserver.models.User;
import com.chatserver.models.draft.SubscribeDraft;
import com.chatserver.repository.ChatRoomRepository;
import com.chatserver.repository.SubscribeRepository;
import com.chatserver.repository.UserRepository;
import com.chatserver.result.Result;
import com.chatserver.service.SubscribeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscribeServiceImpl implements SubscribeService {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final SubscribeRepository subscribeRepository;

    public SubscribeServiceImpl(UserRepository userRepository, ChatRoomRepository chatRoomRepository, SubscribeRepository subscribeRepository) {
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.subscribeRepository = subscribeRepository;
    }

    @Override
    public Result subscribe(long userId, long chatId) {
        Optional<User> user = userRepository.get(userId);
        if(!user.isPresent()){
            Result.failed("User does not exist");
        }

        Optional<ChatRoom> chatRoom = chatRoomRepository.get(chatId);
        if(!chatRoom.isPresent()){
            Result.failed("Chat room does not exist");
        }

        subscribeRepository.insert(new SubscribeDraft().draft().chatRoomId(chatId).userId(userId).build());
        return Result.succeeded();
    }
}
