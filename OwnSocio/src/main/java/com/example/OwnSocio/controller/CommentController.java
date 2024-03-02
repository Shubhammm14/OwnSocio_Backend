package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.Comment;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.services.CommentService;
import com.example.OwnSocio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @PostMapping("/post/{postId}")
    public Comment putPostComment(@RequestBody Comment comment,@PathVariable Integer postId, @RequestHeader ("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwt(jwt);
        return commentService.createPostComment(comment,postId,user.getId());
    }
    @PostMapping("/reel/{reelId}")
    public Comment putReelComment(@RequestBody Comment comment,@PathVariable Integer reelId,@RequestHeader("Authorization")String jwt)throws Exception{
        User user=userService.findUserByJwt(jwt);
        return commentService.createReelComment(comment,reelId, user.getId());
    }

    @PutMapping("/post/like/{commentId}")
    public Comment commentLike(@PathVariable Integer commentId,@RequestHeader ("Authorization") String jwt)
    {
        User user=userService.findUserByJwt(jwt);
        return commentService.likeComment(commentId,user.getId());
    }
}
