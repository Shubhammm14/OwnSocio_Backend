package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),user.getGender(),user.getFollowers(),user.getFollowings()));
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
        User user1=userRepository.getById(userId1);
        User user2=userRepository.getById(userId2);
        user1.getFollowers().add(userId1);
        user2.getFollowers().add(userId2);
        userRepository.save(user1);
        userRepository.save(user2);
        // You may implement the logic to establish a "follow" relationship between two users here
        // This could involve updating the database to reflect the relationship
        return user1; // For now, returning null as the implementation is not provided
    }

    @Override
    public User updateUser(User user,Integer userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new Exception("User Does not exist with the id " + userId);
        }
        User existingUser = userOptional.get();
        if(user.getFirstName() != null)
            existingUser.setFirstName(user.getFirstName());
        if(user.getLastName() != null)
            existingUser.setLastName(user.getLastName());
        if(user.getEmail() != null)
            existingUser.setEmail(user.getEmail());
        if(user.getPassword() != null)
            existingUser.setPassword(user.getPassword());
        if(user.getFollowings()!=null)
            existingUser.setFollowings(user.getFollowings());
        if(user.getFollowers()!=null)
            existingUser.setFollowers(user.getFollowers());
        return userRepository.save(existingUser);
    }

    @Override
    public List<User> searchUserByQuery(String query) {
        // You may implement the logic to search for users based on a query here
        // This could involve searching user records based on certain criteria like name, email, etc.
        return null; // For now, returning null as the implementation is not provided
    }
}
