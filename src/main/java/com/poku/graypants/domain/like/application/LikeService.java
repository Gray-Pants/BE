package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.application.dto.LikeRequestDto;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.like.persistence.LikeFactory;
import com.poku.graypants.domain.like.persistence.LikeRepository;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final LikeFactory likeFactory;

    public LikeResponseDto addLike(LikeRequestDto requestDto) {
        User user = userService.getUserById(requestDto.getUserId());
        Item item = itemService.getItemById(requestDto.getItemId());

        Like like = likeFactory.createLike(user, item);
        Like savedLike = likeRepository.save(like);

        return new LikeResponseDto(savedLike.getLikeId(), savedLike.getUser().getUserId(), savedLike.getItem().getItemId());
    }

    public List<LikeResponseDto> getLikesByUser(Long userId) {
        User user = userService.getUserById(userId);
        List<Like> likes = likeRepository.findByUser(user);

        return likes.stream()
                .map(like -> new LikeResponseDto(like.getLikeId(), user.getUserId(), like.getItem().getItemId()))
                .collect(Collectors.toList());
    }

    public LikeResponseDto findById(Long likeId) {
        Like like = getLikeById(likeId);
        return new LikeResponseDto(like.getLikeId(), like.getUser().getUserId(), like.getItem().getItemId());
    }

    public void removeLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    private Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.LIKE_NOT_FOUND));
    }

}
