package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.like.application.LikeService;
import com.poku.graypants.domain.like.application.dto.LikeRequestDto;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeResponseDto> addLike(@RequestBody LikeRequestDto requestDto) {
        LikeResponseDto responseDto = likeService.addLike(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeResponseDto>> getLikesByUser(@PathVariable Long userId) {
        List<LikeResponseDto> likes = likeService.getLikesByUser(userId);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<LikeResponseDto> findById(@PathVariable Long likeId) {
        LikeResponseDto responseDto = likeService.findById(likeId);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> removeLike(@PathVariable Long likeId) {
        likeService.removeLike(likeId);
        return ResponseEntity.noContent().build();
    }
}