package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.exception.UserExcepition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User registerUser(User user);
    public User findUserById(Integer userId);
    public User findUserByEmail(String email);
    public User followUser(Integer userId1,Integer userId2);
    public User updateUser(User user,Integer userId) throws UserExcepition;
    public List<User> searchUserByQuery(String query);
    public User findUserByJwt( String token);
}
