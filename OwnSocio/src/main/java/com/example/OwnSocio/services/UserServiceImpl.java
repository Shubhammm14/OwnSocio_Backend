package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.config.JwtProvider;
import com.example.OwnSocio.exception.UserExcepition;
import com.example.OwnSocio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getGender(), user.getFollowers(), user.getFollowings(),user.getSavedPost()));
    }

    @Override
    public User findUserById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) {
        // Retrieve user1 and user2 from the repository
        User user1 = userRepository.findById(userId1).orElse(null);
        User user2 = userRepository.findById(userId2).orElse(null);

        // Check if both users exist
        if (user1 == null || user2 == null) {
            // If any user is not found, return null or throw an exception as per your requirement
            return null;
        }

        // Initialize followers list if null
        if (user1.getFollowers() == null) {
            user1.setFollowers(new ArrayList<>());
        }
        if (user2.getFollowers() == null) {
            user2.setFollowers(new ArrayList<>());
        }

        // Initialize followings list if null
        if (user1.getFollowings() == null) {
            user1.setFollowings(new ArrayList<>());
        }
        if (user2.getFollowings() == null) {
            user2.setFollowings(new ArrayList<>());
        }

        // Add user2 to the followers list of user1 and user1 to the followings list of user2
        user2.getFollowers().add(userId1);
        user1.getFollowings().add(userId2);

        // Save both users in a single operation
        userRepository.saveAll(List.of(user1, user2));

        return user1; // Return user1 or user2 as per your requirement
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserExcepition {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserExcepition("User does not exist with the id " + userId);
        }
        User existingUser = userOptional.get();
        if (user.getFirstName() != null)
            existingUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null)
            existingUser.setLastName(user.getLastName());
        if (user.getEmail() != null)
            existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null)
            existingUser.setPassword(user.getPassword());
        if (user.getFollowings() != null)
            existingUser.setFollowings(user.getFollowings());
        if (user.getFollowers() != null)
            existingUser.setFollowers(user.getFollowers());
        if(user.getGender()!=null)
            existingUser.setGender(user.getGender());
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> searchUserByQuery(String query) {
        List<User> l = userRepository.searchUser(query);

        // You may implement the logic to search for users based on a query here
        // This could involve searching user records based on certain criteria like name, email, etc.
        return l; // For now, returning null as the implementation is not provided
    }

    @Override
    public User findUserByJwt(String token) {
        String email= JwtProvider.getEmailFromJwtToken(token);
        User user= userRepository.findByEmail(email);
        user.setPassword(null);
        return user;
    }
}
