package com.chatserver.config.websocket;

import com.chatserver.result.EntityResult;
import com.chatserver.result.Result;
import com.chatserver.service.ChatRoomService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.security.Principal;

public class SubscriptionInterceptor implements ChannelInterceptor {
    private final ChatRoomService chatRoomService;

    public SubscriptionInterceptor(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor= StompHeaderAccessor.wrap(message);
        if (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand())) {
            Principal userPrincipal = headerAccessor.getUser();
            Result result = chatRoomService.validateUser(headerAccessor.getDestination(), userPrincipal.getName());
            if(result.hasError() || !((EntityResult<Boolean>)result).getEntity())
            {
                throw new IllegalArgumentException("No permission for this topic");
            }
        }

        return message;
    }
}
