package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.like.application.LikeService;
import com.poku.graypants.domain.like.application.dto.LikeRequestDto;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;

import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<ApiResult<LikeResponseDto>> addLike(Authentication authentication, @RequestBody LikeRequestDto requestDto) {
        LikeResponseDto responseDto = likeService.addLike(requestDto, (Long) authentication.getPrincipal());
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResult<List<LikeResponseDto>>> getLikesByUser(Authentication Authentication) {
        List<LikeResponseDto> likes = likeService.getLikesByUserId((Long) Authentication.getPrincipal());
        return new ResponseEntity<>(success(likes), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<ApiResult<LikeResponseDto>> findById(@PathVariable Long likeId) {
        LikeResponseDto responseDto = likeService.findById(likeId);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<ApiResult<Void>> removeLike(@PathVariable Long likeId) {
        likeService.removeLike(likeId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }
}