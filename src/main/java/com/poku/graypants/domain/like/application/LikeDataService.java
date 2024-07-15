package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.like.persistence.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeDataService {

    private final LikeRepository likeRepository;

    public Long getLikesCountByItemId(Long itemId) {
        return likeRepository.countByItem_ItemId(itemId);
    }

    public Boolean isLikedItem(Long userId, Long itemId) {
        return likeRepository.existsByUser_UserIdAndItem_ItemId(userId, itemId);
    }
}
