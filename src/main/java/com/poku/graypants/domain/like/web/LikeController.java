package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.like.application.LikeService;
import com.poku.graypants.domain.like.application.dto.LikeRequestDto;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeResponseDto> addLike(Authentication authentication, @RequestBody LikeRequestDto requestDto) {
        LikeResponseDto responseDto = likeService.addLike(requestDto, (Long) authentication.getPrincipal());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/my")
    public ResponseEntity<List<LikeResponseDto>> getLikesByUser(Authentication Authentication) {
        List<LikeResponseDto> likes = likeService.getLikesByUser((Long) Authentication.getPrincipal());
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