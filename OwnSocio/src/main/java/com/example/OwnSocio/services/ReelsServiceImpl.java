package com.example.OwnSocio.services;

import com.example.OwnSocio.Modal.Reels;
import com.example.OwnSocio.Modal.User;
import com.example.OwnSocio.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelsServiceImpl implements ReelsService{
    @Autowired
    private ReelsRepository reelsRepository;
    @Autowired
    private UserService userService;
    @Override
    public Reels createReel(Reels reel, User user) {
        Reels reels=new Reels();
        if(reel.getTitle()!=null)
            reels.setTitle(reel.getTitle());
        if(reel.getVideo()!=null)
            reels.setVideo(reel.getVideo());
        reels.setUser(user);
        return reelsRepository.save(reels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUserReels(Integer userId) {
        return reelsRepository.findByUserId(userId);

    }

    @Override
    public Reels findReelById(Integer reelId) {
        return reelsRepository.findById(reelId).get();
    }
}
