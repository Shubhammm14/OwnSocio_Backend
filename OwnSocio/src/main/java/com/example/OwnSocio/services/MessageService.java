package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Chat;
import com.example.OwnSocio.Modal.Message;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MessageService {
    public Message createMessage(Integer userId, Integer chatId, Message req);
    public List<Message> findChatMessages(Integer chatId);
}
