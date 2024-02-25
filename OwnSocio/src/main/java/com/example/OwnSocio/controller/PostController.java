package com.example.OwnSocio.controller;

import com.example.OwnSocio.Modal.Post;
import com.example.OwnSocio.repository.UserRepository;
import com.example.OwnSocio.response.ApiResponse;
import com.example.OwnSocio.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post ,@PathVariable  Integer userId){

        Post createPost=postService.createNewPost(post,userId);
        return  new ResponseEntity<>(createPost, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        String message=postService.deletePost(postId,userId);
        return new ResponseEntity<>(new ApiResponse(message,true),HttpStatus.GONE);

    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }
    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) throws Exception {
        List<Post> posts=postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
        @GetMapping("/posts")
        public ResponseEntity<List<Post>> findAllPost(){
        List<Post> posts =postService.findAllPost();
        return new ResponseEntity<>(posts,HttpStatus.OK);
        }
        @PutMapping("/posts/{postId}/user/{userId}")
        public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post post=postService.savedPost(postId,userId);
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
        }
        
        @PutMapping("/posts/like/{postId}/user/{userId}")
        public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        Post post =postService.likePost(postId,userId);
        return new ResponseEntity<>(post,HttpStatus.OK);
        }





}
