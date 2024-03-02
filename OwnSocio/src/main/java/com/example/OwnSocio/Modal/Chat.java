package com.example.OwnSocio.Modal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Chat {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer Id;

private String chat_name;
private String chat_image;
@ManyToMany
private List<User> users=new ArrayList<>();
private LocalDateTime timestamp;
@OneToMany(mappedBy = "chat")
private List<Message> messages=new ArrayList<>();

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getChat_name() {
        return chat_name;
    }

    public void setChat_name(String chat_name) {
        this.chat_name = chat_name;
    }

    public String getChat_image() {
        return chat_image;
    }

    public void setChat_image(String chat_image) {
        this.chat_image = chat_image;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Chat() {
    }

    public Chat(Integer id, String chat_name, String chat_image, List<User> users, LocalDateTime timestamp, List<Message> messages) {
        Id = id;
        this.chat_name = chat_name;
        this.chat_image = chat_image;
        this.users = users;
        this.timestamp = timestamp;
        this.messages = messages;
    }
}
