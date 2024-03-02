package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.Chat;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.services.ChatService;
import com.example.OwnSocio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @PostMapping("/user/chat")
    public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody User user2){
        User usr=userService.findUserById(user2.getId());
        User reqUser=userService.findUserByJwt(jwt);
        return chatService.createChat(reqUser,usr);
    }
    @GetMapping("/chats")
    public List<Chat> finduserchats(@RequestHeader("Authorization") String jwt){
        User user= userService.findUserByJwt(jwt);
        return chatService.findUsersChat(user.getId());
    }

}
