package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.like.persistence.LikeRepository;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final ItemService itemService;

    public List<Item> getLikeItems(String email) {
        Like like = userService.getLikeByEmail(email);
        return likeRepository.findBy
    }
}
