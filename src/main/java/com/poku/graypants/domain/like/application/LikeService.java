package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.persistence.LikeRepository;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Item> getLikesByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return LikeRepository.findByUser(user);
        } else {
            // 사용자를 찾을 수 없을 때 빈 리스트를 반환하거나 예외 던짐
            return List.of(); // 빈 리스트 반환
        }
    }
}
