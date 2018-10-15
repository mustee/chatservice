package com.chatserver.controllers;


import com.chatserver.controllers.models.CreateChatRoomModel;
import com.chatserver.controllers.models.SubscribeModel;
import com.chatserver.result.Result;
import com.chatserver.service.ChatRoomService;
import com.chatserver.service.MessageService;
import com.chatserver.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomContoller {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final SubscribeService subscribeService;

    @Autowired
    public ChatRoomContoller(ChatRoomService chatRoomService, MessageService messageService, SubscribeService subscribeService){
        this.chatRoomService = chatRoomService;
        this.messageService = messageService;
        this.subscribeService = subscribeService;
    }


    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> createChatRoom(@Valid @RequestBody CreateChatRoomModel model){
        Result result = chatRoomService.createChatRoom(model.getName());
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> getAll(){
        Result result = chatRoomService.listChatRooms();
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }



    @PutMapping(value = "{id}/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> subscribe(@Valid @RequestBody SubscribeModel model, @PathVariable long id){
        Result result = subscribeService.subscribe(model.getUserId(), id);
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "{id}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> messages(@PathVariable long id){
        Result result = messageService.listChatRoomMessages(id);
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }

}
