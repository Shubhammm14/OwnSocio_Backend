package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public Comment createPostComment(Comment comment,Integer postId,Integer userId) throws Exception;
    public Comment createReelComment(Comment comment,Integer reelId,Integer userId)throws Exception;
    public Comment findCommentById(Integer commentId) throws Exception;
    public Comment likeComment(Integer CommentId,Integer userId);
}
