package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Chat;
import com.example.OwnSocio.Modal.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ChatService {
    public Chat createChat(User reqUser,User user2);
    public Chat findChatById(Integer chatId);
    public List<Chat> findUsersChat(Integer userId);
}
