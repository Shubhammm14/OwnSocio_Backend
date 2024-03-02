package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.Message;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.services.MessageService;
import com.example.OwnSocio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @GetMapping("/create/msg/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId,@RequestBody Message msg){
        User user=userService.findUserByJwt(jwt);
        return messageService.createMessage(user.getId(),chatId,msg);
    }
    @GetMapping("chat/{chatId}/messages")
    public List<Message> findChatMessages(@PathVariable Integer chatId){
        return messageService.findChatMessages(chatId);
    }
}
