package com.poku.graypants.domain.like.application;

import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.domain.like.application.dto.LikecreateRequestDto;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.like.persistence.LikeRepository;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeResponseDto createLike(LikecreateRequestDto likecreateRequestDto) {
        return new LikeResponseDto(likeRepository.save(likecreateRequestDto.toEntity()));
    }

    public List<LikeResponseDto> getLikes(Like like) {
        return likeRepository.findAll(like);
    }

    public LikeResponseDto findByUserId(Long id) {
        Like like = getLikeById(id);
        return new LikeResponseDto(like);
    }

    public void deleteLike(Long id) {
        likeRepository.deleteById(id);
    }

    private Like getLikeById(Long id) {
        return likeRepository.findById(id).orElseThrow(() -> new GrayPantsException(ExceptionStatus.Like_NOT_FOUND));
    }

}
