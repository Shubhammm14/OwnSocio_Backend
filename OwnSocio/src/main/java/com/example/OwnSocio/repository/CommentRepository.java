package com.example.OwnSocio.repository;

import com.example.OwnSocio.Modal.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
