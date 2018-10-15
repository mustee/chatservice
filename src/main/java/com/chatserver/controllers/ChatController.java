package com.chatserver.controllers;


import com.chatserver.controllers.models.ChatMessage;
import com.chatserver.models.User;
import com.chatserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Optional;

@Controller
public class ChatController {
    private UserRepository userRepository;
    private SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template, UserRepository userRepository) {
        this.template = template;
        this.userRepository = userRepository;
    }

    @MessageMapping("/chatrooms/{room}/message")
    public void sendChatRoomMessage(@Payload ChatMessage message, @DestinationVariable String room, Principal principal) {
        message.setUsername(principal.getName());

        template.convertAndSend("/topic/chatroom/" + room, message);
    }


    @MessageMapping("/users/{username}/message")
    public void sendUserMessage(@Payload ChatMessage message, @DestinationVariable("username") String username, Principal principal) {
        message.setUsername(principal.getName());
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent() && user.get().shouldReceiveMessage()){
            template.convertAndSend("/user/" + username + "/exchange/amq.direct/chat.message", message);
        }
    }
}
