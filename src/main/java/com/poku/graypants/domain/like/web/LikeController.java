package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.like.application.LikeService;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.domain.like.application.dto.LikecreateRequestDto;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;


@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{id}") // userId를 인자로 받는다
    public ResponseEntity<ApiResult<LikeResponseDto>> getLike(@PathVariable Long id) {
        LikeResponseDto responseDto = likeService.findByUserId(id);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<ApiResult<LikeResponseDto>> createLike(@RequestBody LikecreateRequestDto likecreateRequestDto) {
        LikeResponseDto responseDto = likeService.createLike(likecreateRequestDto);

        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/{likes}")
    public ResponseEntity<List<LikeResponseDto>> getLikes(@PathVariable Like likes) {
        List<LikeResponseDto> likeList = likeService.getLikes(likes);
        return new ResponseEntity<>(likeList, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLike(@PathVariable Long id) {
        likeService.deleteLike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}