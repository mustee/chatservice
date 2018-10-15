package com.chatserver.service.impl;

import com.chatserver.models.ChatRoom;
import com.chatserver.models.User;
import com.chatserver.models.draft.ChatRoomDraft;
import com.chatserver.repository.ChatRoomRepository;
import com.chatserver.repository.UserRepository;
import com.chatserver.result.EntityResult;
import com.chatserver.result.ListResult;
import com.chatserver.result.Result;
import com.chatserver.service.ChatRoomService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, UserRepository userRepository){
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Result createChatRoom(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Name cannot be null" );

        Optional<ChatRoom> chatRoom = chatRoomRepository.findByName(name);
        if(chatRoom.isPresent()){
            return Result.failed("Chat room already exists");
        }

        chatRoomRepository.insert(new ChatRoomDraft().draft().name(name).dateCreated(new Date()).build());
        return Result.succeeded();
    }

    @Override
    public Result listChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.getAll();
        return new ListResult<>(chatRooms);
    }

    @Override
    public Result validateUser(String chatRoom, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            new EntityResult<>(false);
        }

        List<ChatRoom> chatRooms = chatRoomRepository.findByUserId(user.get().getId());
        return new EntityResult<>(chatRooms.stream().anyMatch(c -> c.getName().equals(chatRoom)));
    }
}
