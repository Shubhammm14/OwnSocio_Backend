package com.example.OwnSocio.repository;

import com.example.OwnSocio.Modal.Chat;
import com.example.OwnSocio.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    @Query("select c from Chat c join c.users u where u.id = :userId")
    List<Chat> findByUserId(@Param("userId") Integer userId);
    @Query("select c from Chat c where :user member of c.users and :reqUser member of c.users")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

}
