package com.chatserver.controllers;

import com.chatserver.controllers.models.CreateUserModel;
import com.chatserver.models.User;
import com.chatserver.result.EntityResult;
import com.chatserver.result.Result;
import com.chatserver.service.MessageService;
import com.chatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public UserController(UserService userService, MessageService messageService){
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping(value = "/signup",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> signUp(@Valid @RequestBody CreateUserModel model){
        Result result = userService.signUp(model.getUsername(), model.getPassword());
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "current", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> current(Principal principal){
        String name = principal.getName();
        Result result = userService.findByUsername(name);
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(((EntityResult<User>)result).getEntity());
    }

    @PostMapping(value = "/signout",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> signOut(@RequestParam String token){
        Result result = userService.signOut(token);
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }



    @PutMapping(value = "{id}/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> subscribe(@PathVariable long id){
        Result result = userService.subscribe(id);
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }



    @GetMapping(value = "{id}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> messages(@PathVariable long id){
        Result result = messageService.listUserMessages(id);
        if(result.hasError()){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(result);
    }

}
