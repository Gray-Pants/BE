package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final LikeFactory likeFactory;

    public Like addLike(LikeRequestDto requestDto, Long userId) {
        User user = userService.getVerifyUserByUserId(userId);
        Item item = itemService.getVerifyItemById(requestDto.getItemId());

        Like like = likeFactory.createLike(user, item);
        return likeRepository.save(like);
    }
    public void changeLike(LikeRequestDto requestDto, Long userId) {
        Optional<Like> like = likeRepository.findByUser_UserIdAndItem_ItemId(userId, requestDto.getItemId());
        if(like.isEmpty())
            addLike(requestDto, userId);
        else
            removeLike(like.get().getLikeId());
    }

    public List<LikeResponseDto> getLikesByUserId(Long userId) {
        List<Like> likes = likeRepository.findByUser_UserId(userId);

        return likes.stream()
                .map(like -> new LikeResponseDto(like.getLikeId(), userId,
                        new ItemResponseDto(like.getItem())))
                .collect(Collectors.toList());
    }

    public LikeResponseDto findById(Long likeId) {
        Like like = getLikeById(likeId);
        return new LikeResponseDto(like.getLikeId(), like.getUser().getUserId(), new ItemResponseDto(like.getItem()));
    }

    public void removeLike(Long likeId) {
        Like like = getLikeById(likeId);
        likeRepository.delete(like);
    }

    private Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.LIKE_NOT_FOUND));
    }

}
