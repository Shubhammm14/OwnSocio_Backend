package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.UserRepository;
import com.example.OwnSocio.response.ApiResponse;
import com.example.OwnSocio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/Api/user/{userId}")
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
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        userRepository.deleteById(userId);
        return new ResponseEntity<>(new ApiResponse("User with id " + userId + " has been deleted successfully.",true), HttpStatus.GONE);
    }
    @PutMapping("/follow/users/{user1}/{user2}")
    public ResponseEntity<User> followUserHandler(@PathVariable Integer user1,@PathVariable Integer user2){
        User user=userService.followUser(user1,user2);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/user/search")
    public ResponseEntity<List<User>> userQueryHandler(@RequestParam ("query")String query){
        List<User> users=userService.searchUserByQuery(query);
        return new ResponseEntity<>(users,HttpStatus.GONE);
    }


}
