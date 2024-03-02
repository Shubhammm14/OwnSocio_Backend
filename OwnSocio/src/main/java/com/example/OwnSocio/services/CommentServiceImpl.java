package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Comment;
import com.example.OwnSocio.Modal.Post;
import com.example.OwnSocio.Modal.Reels;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.CommentRepository;
import com.example.OwnSocio.repository.PostRepository;
import com.example.OwnSocio.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private ReelsService reelsService;
    @Override
    public Comment createReelComment(Comment comment, Integer reelId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found with id: " + userId);
        }
        Reels reel=reelsService.findReelById(reelId);
        if(reel==null)
            throw new Exception("Reel not found");
        Comment nComment=new Comment();
        nComment.setCreatedAt(LocalDateTime.now());
        nComment.setUser(user);
        nComment.setContent(comment.getContent());
        Comment saveComment=commentRepository.save(nComment);
        reel.getComments().add(saveComment);
        reelsRepository.save(reel);
        return saveComment;
    }
    @Override
    public Comment createPostComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new Exception("User not found with id: " + userId);
        }

        Post post = postService.findPostById(postId);
        if (post == null) {
            throw new Exception("Post not found with id: " + postId);
        }

        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setUser(user);
        newComment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(newComment);

        post.getComments().add(savedComment); // Add the saved comment
        postRepository.save(post);

        return savedComment; // Return the saved comment
    }



    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        return commentOptional.orElseThrow(() -> new Exception("Invalid commentId")); // Throws exception if comment not found
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return null; // Or throw an exception, depending on your requirements
        }

        User user = userService.findUserById(userId);
        if (user != null) {
            if(!comment.getLiked().contains(user))
            comment.getLiked().add(user);
            else
                comment.getLiked().remove(user);
            return commentRepository.save(comment);
        }

        return null; // Or handle appropriately if user is not found
    }
}

