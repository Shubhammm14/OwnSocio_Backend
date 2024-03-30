package com.example.OwnSocio.repository;

import com.example.OwnSocio.Modal.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query("select p from Post p where p.user.id=:userId")
    List<Post> findPostByUserId(Integer userId);
    @Query("SELECT p FROM Post p JOIN FETCH p.user") // Fetch the user along with the post
    List<Post> findAllPostWithUserDetails();
}
