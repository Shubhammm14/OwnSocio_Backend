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
@RequestMapping("/Api")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public User getUserById(@RequestHeader ("Authorization") String jwt) throws Exception {
        Integer userId=userService.findUserByJwt(jwt).getId();
        if(userService.findUserById(userId)!=null)
          return userService.findUserById(userId);
      else
          throw new Exception("user does not exist");
    }

    @GetMapping("/user/profile")
    public User getUserProfile(@RequestHeader ("Authorization") String jwt){
        return userService.findUserByJwt(jwt);
    }
    @PutMapping("/update/user")
    public User updateUserDetail(@RequestBody User user,@RequestHeader("Authorization") String jwt) throws Exception {
         Integer userId=userService.findUserByJwt(jwt).getId();
        return userService.updateUser(user,userId);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<ApiResponse> deleteUser(@RequestHeader("Authorization") String jwt){
        Integer userId=userService.findUserByJwt(jwt).getId();
        userRepository.deleteById(userId);
        return new ResponseEntity<>(new ApiResponse("User with id " + userId + " has been deleted successfully.",true), HttpStatus.GONE);
    }
    @PutMapping("/follow/user/{user2}")
    public ResponseEntity<User> followUserHandler(@RequestHeader ("Authorization") String jwt,@PathVariable Integer user2){
        Integer userReq=userService.findUserByJwt(jwt).getId();
        User user=userService.followUser(userReq,user2);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/user/search")
    public ResponseEntity<List<User>> userQueryHandler(@RequestParam ("query")String query){
        List<User> users=userService.searchUserByQuery(query);
        return new ResponseEntity<>(users,HttpStatus.GONE);
    }


}
