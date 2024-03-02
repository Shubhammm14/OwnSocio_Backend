package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.Post;
import com.example.OwnSocio.repository.UserRepository;
import com.example.OwnSocio.response.ApiResponse;
import com.example.OwnSocio.services.PostService;
import com.example.OwnSocio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Api")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @PostMapping("/posts/user")
    public ResponseEntity<Post> createPost(@RequestBody Post post ,@RequestHeader("Authorization") String jwt){
        Integer userId=userService.findUserByJwt(jwt).getId();
        Post createPost=postService.createNewPost(post,userId);
        return  new ResponseEntity<>(createPost, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/posts/delete/{postId}/user")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        Integer userId=userService.findUserByJwt(jwt).getId();
        String message=postService.deletePost(postId,userId);
        return new ResponseEntity<>(new ApiResponse(message,true),HttpStatus.GONE);

    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }
    @GetMapping("/posts/user")
    public ResponseEntity<List<Post>> findUserPost(@RequestHeader ("Authorization") String jwt) throws Exception {
        Integer userId=userService.findUserByJwt(jwt).getId();
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
        @GetMapping("/posts")
        public ResponseEntity<List<Post>> findAllPost(){
        List<Post> posts =postService.findAllPost();
        return new ResponseEntity<>(posts,HttpStatus.OK);
        }
        @PutMapping("/posts/save/{postId}/user")
        public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@RequestHeader ("Authorization")String jwt) throws Exception {
        Integer userId=userService.findUserByJwt(jwt).getId();
        Post post=postService.savedPost(postId,userId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
        }
        
        @PutMapping("/posts/like/{postId}/user")
        public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@RequestHeader ("Authorization") String jwt) throws Exception {
        Integer userId=userService.findUserByJwt(jwt).getId();
        Post post =postService.likePost(postId,userId);
        return new ResponseEntity<>(post,HttpStatus.OK);
        }
}
