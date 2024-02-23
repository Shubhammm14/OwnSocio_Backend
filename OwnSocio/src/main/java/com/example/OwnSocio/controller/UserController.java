package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.UserRepository;
import com.example.OwnSocio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Integer userId) throws Exception {
      if(userService.findUserById(userId)!=null)
          return userService.findUserById(userId);
      else
          throw new Exception("user does not exist");
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PutMapping("/updateuser/{userId}")
    public User updateUserDetail(@RequestBody User user, @PathVariable Integer userId) throws Exception {

        return userService.updateUser(user,userId);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public String deleteUser(@PathVariable Integer userId){
        userRepository.deleteById(userId);
        return "User with id " + userId + " has been deleted successfully.";
    }
}
