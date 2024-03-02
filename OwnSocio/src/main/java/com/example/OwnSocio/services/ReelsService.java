package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Reels;
import com.example.OwnSocio.Modal.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReelsService {

  public Reels createReel(Reels reel, User user);
  public List<Reels> findAllReels();
  public List<Reels> findUserReels(Integer userId);
  public Reels findReelById(Integer reelId);
}
