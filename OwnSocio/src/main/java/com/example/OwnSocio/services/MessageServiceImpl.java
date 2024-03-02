package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Chat;
import com.example.OwnSocio.Modal.Message;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.ChatRepository;
import com.example.OwnSocio.repository.MessageRepository;
import com.example.OwnSocio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Message createMessage(Integer userId, Integer chatId, Message req) {
        Chat chat=chatRepository.findById(chatId).get();
        User user=userRepository.findById(userId).get();
        Message msg=new Message();
        msg.setChat(chat);
        msg.setContent(req.getContent());
        msg.setUser(user);
        msg.setImage(req.getImage());
        msg.setVideo(req.getVideo());
        msg.setTimeStamp(LocalDateTime.now());
        Message savedMsg= messageRepository.save(msg);
        Chat cht=chatRepository.findById(chatId).get();
        cht.getMessages().add(savedMsg);
        chatRepository.save(cht);
        return savedMsg;
    }

    @Override
    public List<Message> findChatMessages(Integer chatId) {

        Chat chat=chatRepository.findById(chatId).get();
        return messageRepository.findByChatId(chatId);
    }
}
