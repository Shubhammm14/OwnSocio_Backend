package com.example.OwnSocio.repository;

import com.example.OwnSocio.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
  public User findByEmail(String email);
}
