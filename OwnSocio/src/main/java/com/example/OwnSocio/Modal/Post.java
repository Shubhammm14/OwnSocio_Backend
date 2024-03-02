package com.example.OwnSocio.Modal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;
    private String image;
    private String video;
    @OneToMany
   private List<Comment> comments=new ArrayList<>();
    @ManyToOne

    private User user;


    private LocalDateTime createdAt;

    @OneToMany
    private List<User> liked = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public Post(Integer id, String caption, String image, String video, List<Comment> comments, User user, LocalDateTime createdAt, List<User> liked) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.comments = comments;
        this.user = user;
        this.createdAt = createdAt;
        this.liked = liked;
    }

    public Post() {
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", imageUrl='" + image + '\'' +
                ", videoUrl='" + video + '\'' +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
