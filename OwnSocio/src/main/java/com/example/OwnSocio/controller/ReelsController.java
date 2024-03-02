package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.Reels;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.ReelsRepository;
import com.example.OwnSocio.services.ReelsService;
import com.example.OwnSocio.services.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api")
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private UserService userService;
    @GetMapping("/reels")
    public Reels createReel(@RequestBody Reels reel, @RequestHeader("Authorization")String jwt){
        return reelsService.createReel(reel,userService.findUserByJwt(jwt));
    }
    @GetMapping("/reels/All")
    public List<Reels> findAllReels(){
        return reelsService.findAllReels();
    }
    @GetMapping("/reels/user")
    public List<Reels> findUsersReel(@RequestHeader("Authorization") String jwt){
        User user=userService.findUserByJwt(jwt);
        return reelsService.findUserReels(user.getId());
    }
}
