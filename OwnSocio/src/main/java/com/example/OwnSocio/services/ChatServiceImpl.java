package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Chat;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExit=chatRepository.findChatByUsersId(user2,reqUser);
        if(isExit!=null)
            return isExit;
        Chat chat=new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }


    @Override
    public Chat findChatById(Integer chatId) {
        return chatRepository.findById(chatId).get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUserId(userId);
    }
}
