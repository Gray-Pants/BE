package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.like.persistence.LikeRepository;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeResponseDto findById(Long id) {
        Like like = getLikeByID(id);
        return new LikeResponseDto(like);
    }

    private Like getLikeByID(Long id) {
        Like like = likeRepository.findById(id).orElseThrow(() ->
            {throw new RuntimeException(ExceptionStatus.ITEM_NOT_FOUND.getMessage());
            });
        return like;
    }

    public List<LikeResponseDto> findByUserId(Long id) {
        Like like = getLikeByID(id);
        return new List<LikeResponseDto>(like);
    }
}
